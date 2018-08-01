package com.orm.v_1.ORM.logic.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.logic.OrmEntity;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.Id;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.query.Query;

public class DatabaseDaoImplementation implements DatabaseDao {
	
	private static final Logger logger = Logger.getLogger( DatabaseDao.class.getName() );
	
	private static final String GENERATED_KEY_COLUMN_NAME = "GENERATED_KEY";
	
	private Database database;
	private Connection connection;
	private DateFormat dateFormatter;
	
	public DatabaseDaoImplementation(Connection connection, Database database) {
		this.database = database;
		this.connection = connection;
		this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public List<?> findAll(Class<?> entity) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException {
		Table table = this.database.getTableForEntityClass(entity);
		String query = String.format("SELECT * FROM `%s`;", table.getName());
		Statement st = this.connection.createStatement();
		logger.info(">>> " + query);
		ResultSet rs = st.executeQuery(query);
		List<Object> results = new LinkedList<>();
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
			results.add(object);
		}
		
		return results;
	}

	@Override
	public Object findOne(Class<?> entity, Object id) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException {
		Object result = entity.newInstance();
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
	}

	@Override
	public Boolean delete(Class<?> entity, Object id) throws SQLException {
		Table table = database.getTableForEntityClass(entity);
		String query = String.format("DELETE FROM `%s` WHERE `%s`=\"%s\";", table.getName(), table.getId().getNameInDb(), id.toString());
		Statement st = connection.createStatement();
		logger.info(">>> " + query);
		st.execute(query);
		return true;
	}

	@Override
	public Object save(Object object) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
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
		
		System.out.println(object.getClass().getDeclaredFields());
		if(genPriKey && rs != null && rs.next()) {
			Integer genValue = rs.getInt(GENERATED_KEY_COLUMN_NAME);
			Field priKeyField = object.getClass().getDeclaredField(primaryKeyModelName);
			priKeyField.setAccessible(true);
			priKeyField.set(object, genValue);
		}

		return object;
	}

	@Override
	public Boolean saveMore(List<OrmEntity> objects) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
		for(OrmEntity object: objects) {
			save(object);
		}
		return true;
	}

	@Override
	public Boolean updateOne(OrmEntity object) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException {
		Table table = database.getTableForEntityClass(object.getClass());
		Field field = object.getClass().getDeclaredField(table.getId().getNameInModel());
		field.setAccessible(true);
		Object idOfObject = field.get(object);
		Object oldObject = findOne(object.getClass(), idOfObject);
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
	}

	@Override
	public Boolean updateMore(List<OrmEntity> objects) throws SQLException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException {
		for(OrmEntity object: objects) {
			updateOne(object);
		}
		return true;
	}

	@Override
	public void executeQuery(String query) throws SQLException {
		Statement st = connection.createStatement();
		st.execute(query);
	}

	@Override
	public List<?> executeQuery(Class<?> entity, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> findBy(Class<?> entity, Query query) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException{
		query.setDb(database, entity);
		String queryRes = query.getQuery();
		logger.info(">>> " + queryRes);
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(queryRes);
		System.out.println("proslo");
		Table table = database.getTableForEntityClass(entity);
		List<Object> results = new LinkedList<>();
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
			results.add(object);
		}

		return results;
	}

}
