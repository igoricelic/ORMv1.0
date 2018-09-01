package com.orm.v_1.ORM.logic.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.orm.v_1.ORM.logic.repositories.DaoRepository;
import com.orm.v_1.ORM.logic.repositories.ProxyRepository;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.Id;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.query.Query;

public class DatabaseDaoImplementation<T> implements ProxyRepository<T> {
	
	private static final Logger logger = Logger.getLogger( DaoRepository.class.getName() );
	
	private static final String GENERATED_KEY_COLUMN_NAME = "GENERATED_KEY";
	
	private Class<T> entity;
	
	private Database database;
	private Connection connection;
	private DateFormat dateFormatter;
	
	public DatabaseDaoImplementation(Class<T> clazz, Connection connection, Database database) {
		entity = clazz;
		this.database = database;
		this.connection = connection;
		this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public List<T> findAll() {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			String query = String.format("SELECT * FROM `%s`;", table.getName());
			Statement st = this.connection.createStatement();
			logger.info(">>> " + query);
			ResultSet rs = st.executeQuery(query);
			return extractResultSet(rs);
		} catch (SecurityException | IllegalArgumentException | SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public T findOne(Object id) {
		T result;
		try {
			result = entity.newInstance();
			Table table = database.getTableForEntityClass(entity);
			String query = String.format("SELECT * FROM `%s` WHERE `%s`=\"%s\";", table.getName(), table.getId().getNameInDb(), id.toString());
			Statement st = connection.createStatement();
			logger.info(">>> " + query);
			ResultSet rs = st.executeQuery(query);
			Field field = null;
			String columnName = "";
			Object value = null;
			while(rs.next()) {
				for(Column column: table.getColumns()) {
					columnName = column.getNameInDb();
					value = rs.getObject(columnName);
					field = entity.getDeclaredField(column.getNameInModel());
					field.setAccessible(true);
					field.set(result, value);
				}
			}
			return result;
		} catch (InstantiationException | IllegalAccessException | SQLException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean delete(Object id) {
		try {
			Table table = database.getTableForEntityClass(entity);
			String query = String.format("DELETE FROM `%s` WHERE `%s`=\"%s\";", table.getName(), table.getId().getNameInDb(), id.toString());
			Statement st;
			st = connection.createStatement();
			logger.info(">>> " + query);
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T save(T object) {
		try {
			Table table = this.database.getTableForEntityClass(object.getClass());
			String query = table.getInsertQuery();
			
			StringBuilder values = new StringBuilder(50);
			Field field = null;
			Object value = null;
			String primaryKeyModelName = null;
			boolean genPriKey = false;
			for(Column column: table.getColumns()) {
				field = object.getClass().getDeclaredField(column.getNameInModel());
				field.setAccessible(true);
				value = field.get(object);
				if(column.isPrimaryKey() && ((Id)column).isAutoIncrement() && (value == null)) {
					values.append("null, ");
					primaryKeyModelName = column.getNameInModel();
					genPriKey = true;
					continue;
				}
				if(field.getType().equals(Date.class)) {
					values.append("\"").append(dateFormatter.format(value)).append("\", ");
				}
				else if(field.getType().equals(String.class)) {
					values.append("\"").append(value).append("\", ");
				}
				else {
					values.append(field.get(object)).append(", ");
				}
			}
			query = String.format(query, values.toString().substring(0, values.toString().length()-2));
			logger.info(">>> " + query);
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(genPriKey && rs != null && rs.next()) {
				Integer genValue = rs.getInt(GENERATED_KEY_COLUMN_NAME);
				Field priKeyField = object.getClass().getDeclaredField(primaryKeyModelName);
				priKeyField.setAccessible(true);
				priKeyField.set(object, genValue);
			}

			return object;
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean saveMore(List<T> objects) {
		for(T object: objects) {
			save(object);
		}
		return true;
	}

	@Override
	public Boolean updateOne(T object) {
		try {
			Table table = database.getTableForEntityClass(object.getClass());
			Field field = object.getClass().getDeclaredField(table.getId().getNameInModel());
			field.setAccessible(true);
			Object idOfObject = field.get(object);
			Object oldObject = findOne(idOfObject);
			Field fieldOldObj = null, fieldObj = null;
			Object valueOldObj = null, valueObj = null;
			StringBuilder sb = new StringBuilder(50);
			for(Column column: table.getColumns()) {
				fieldOldObj = oldObject.getClass().getDeclaredField(column.getNameInModel());
				fieldOldObj.setAccessible(true);
				valueOldObj = fieldOldObj.get(oldObject);
				fieldObj = object.getClass().getDeclaredField(column.getNameInModel());
				fieldObj.setAccessible(true);
				valueObj = fieldObj.get(object);
				if(!valueOldObj.equals(valueObj)) {
					sb.append("`").append(column.getNameInDb()).append("` = ")
					.append("\"").append(valueObj.toString()).append("\", ");
				}
			}
			String changeStr = sb.toString().substring(0, sb.toString().length()-2);
			String query = String.format("UPDATE `%s` SET %s WHERE `%s` = \"%s\";", table.getName(), changeStr, table.getId().getNameInDb(), idOfObject.toString());
			logger.info(">>> " + query);
			Statement st = connection.createStatement();
			st.execute(query);
			return true;
		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean updateMore(List<T> objects) {
		for(T object: objects) {
			updateOne(object);
		}
		return true;
	}

	@Override
	public List<T> findBy(Query query) {
		try {
			query.setDb(database, entity);
			String queryRes = query.getQuery();
			logger.info(">>> " + queryRes);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(queryRes);
			Table table = database.getTableForEntityClass(entity);
			List<T> results = new LinkedList<>();
			Object object = null, value = null;
			Field field = null;
			String columnName = null;
			
			while(rs.next()) {
				object = entity.newInstance();
				for(Column column: table.getColumns()) {
					columnName = column.getNameInDb();
					value = rs.getObject(columnName);
					field = entity.getDeclaredField(column.getNameInModel());
					field.setAccessible(true);
					field.set(object, value);
				}
				results.add((T) object);
			}

			return results;
		} catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> findByNativeQuery(String query) {
		try {
			logger.info("NATIVE QUERY: " + query);
			Statement st = this.connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			return extractResultSet(rs);
		} catch (SQLException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<T> extractResultSet (ResultSet rs) {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			List<T> results = new LinkedList<>();
			Object object = null, value = null;
			Field field = null;
			String columnName = null;
			while(rs.next()) {
				object = entity.newInstance();
				for(Column column: table.getColumns()) {
					columnName = column.getNameInDb();
					value = rs.getObject(columnName);
					field = entity.getDeclaredField(column.getNameInModel());
					field.setAccessible(true);
					field.set(object, value);
				}
				results.add((T) object);
			}
			return results;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean executeNativeQuery(String query) {
		try {
			logger.info("NATIVE QUERY: " + query);
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T executePreparedQueryOne(String preparedQuery, Object[] args) {
		try {
			logger.info(">>> "+preparedQuery);
			PreparedStatement preparedStatement = this.connection.prepareStatement(preparedQuery);
			for(int i=0; i<args.length; i++) {
				preparedStatement.setObject(i+1, args[i]);
			}
			ResultSet rs = preparedStatement.executeQuery();
			List<T> results = extractResultSet(rs);
			// TODO: Proveriti da li je count veci od 1 i ispucati exception ukoliko jeste
			return results.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> executeByPreparedQueryMore(String preparedQuery, Object[] args) {
		try {
			logger.info(">>> "+preparedQuery);
			PreparedStatement preparedStatement = this.connection.prepareStatement(preparedQuery);
			for(int i=0; i<args.length; i++) {
				preparedStatement.setObject(i+1, args[i]);
			}
			ResultSet rs = preparedStatement.executeQuery();
			return extractResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
