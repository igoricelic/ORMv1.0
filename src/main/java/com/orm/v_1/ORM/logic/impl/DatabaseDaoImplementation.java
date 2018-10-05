package com.orm.v_1.ORM.logic.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageImplementation;
import com.orm.v_1.ORM.datastructure.PageRequest;
import com.orm.v_1.ORM.logic.repositories.CrudRepository;
import com.orm.v_1.ORM.logic.repositories.ProxyRepository;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.Id;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.generator.GeneratedResult;
import com.orm.v_1.ORM.queryspecification.generator.SpecificationQueryGenerator;
import com.orm.v_1.ORM.queryspecification.generator.SpecificationQueryGeneratorImpl;

public class DatabaseDaoImplementation<T> implements ProxyRepository<T> {

	private static final Logger logger = Logger.getLogger(CrudRepository.class.getName());

	private static final String GENERATED_KEY_COLUMN_NAME = "GENERATED_KEY";

	private Class<T> entity;

	private Database database;
	
	private Connection connection;
	
	private Boolean logSqlQueries;

	public DatabaseDaoImplementation(Class<T> clazz, Connection connection, Database database, Boolean logSqlQueries) {
		entity = clazz;
		this.database = database;
		this.connection = connection;
		this.logSqlQueries = logSqlQueries;
	}

	@Override
	public List<T> findAll() {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			String query = String.format("SELECT * FROM %s;", table.getName());
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			return extractListFromResultSet(rs);
		} catch (SecurityException | IllegalArgumentException | SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public T findOne(Object id) {
		try {
			Table table = database.getTableForEntityClass(entity);
			String query = String.format("SELECT * FROM %s WHERE %s = ?", table.getName(), table.getId().getNameInDb());
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setObject(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			return extractObjectFromResultSet(rs);
		} catch (SQLException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean delete(Object id) {
		try {
			Table table = database.getTableForEntityClass(entity);
			String query = String.format("DELETE FROM %s WHERE %s = ?", table.getName(), table.getId().getNameInDb());
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			if(logSqlQueries) logger.info(query);
			preparedStatement.setObject(1, id);
			preparedStatement.execute();
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
			String query = "INSERT INTO %s (%s) VALUES (%s)";
			Id id = table.getId();
			StringBuilder sbColumns = new StringBuilder(50);
			StringBuilder sbValues = new StringBuilder(50);

			int idx = 0;
			for (Column column : table.getColumns()) {
				if(column.isPrimaryKey() && table.getId().isAutoIncrement()) continue;
				if (idx == 0) {
					sbColumns.append(column.getNameInDb());
					sbValues.append("?");
				} else {
					sbColumns.append(", ").append(column.getNameInDb());
					sbValues.append(", ?");
				}
				idx++;
			}
			query = String.format(query, table.getName(), sbColumns.toString(), sbValues.toString());
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			idx = 1;
			for (Column column : table.getColumns()) {
				if(column.isPrimaryKey() && id.isAutoIncrement()) continue;
				Field field = column.getField();
				field.setAccessible(true);
				Object value = field.get(object);
				preparedStatement.setObject(idx, value);
				idx++;
			}

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			
			if (id.isAutoIncrement() && rs != null && rs.next()) {
				Integer genValue = rs.getInt(GENERATED_KEY_COLUMN_NAME);
				Field priKeyField = id.getField();
				priKeyField.setAccessible(true);
				priKeyField.set(object, genValue);
			}

			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean saveMore(List<T> objects) {
		for (T object : objects) {
			save(object);
		}
		return true;
	}
	
	@Override
	public Boolean saveMoreAnotherWay(List<T> objects) {
		try {
			if(objects.size() == 0) return true;
			Class<?> entityClass = objects.get(0).getClass();
			Table table = this.database.getTableForEntityClass(entityClass);
			String query = "INSERT INTO %s VALUES ";
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateMore(List<T> objects) {
		for (T object : objects) {
			updateOne(object);
		}
		return true;
	}

	@Override
	public List<T> findByNativeQuery(String query) {
		try {
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			return extractListFromResultSet(rs);
		} catch (SQLException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<T> extractListFromResultSet(ResultSet rs) {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			List<T> results = new LinkedList<>();
			T object = null;
			Object value = null;
			Field field = null;
			String columnName = null;
			while (rs.next()) {
				object = entity.newInstance();
				for (Column column : table.getColumns()) {
					columnName = column.getNameInDb();
					value = rs.getObject(columnName);
					field = column.getField();
					field.setAccessible(true);
					field.set(object, value);
				}
				results.add(object);
			}
			return results;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | SecurityException | InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private T extractObjectFromResultSet(ResultSet rs) {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			T object = null;
			Object value = null;
			Field field = null;
			String columnName = null;
			while (rs.next()) {
				object = entity.newInstance();
				for (Column column : table.getColumns()) {
					columnName = column.getNameInDb();
					value = rs.getObject(columnName);
					field = column.getField();
					field.setAccessible(true);
					field.set(object, value);
				}
			}
			return object;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | SecurityException | InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean executeNativeQuery(String query) {
		try {
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T executePreparedQueryOne(String preparedQuery, Object[] args) {
		try {
			if(logSqlQueries) logger.info(preparedQuery);
			PreparedStatement preparedStatement = getConnection().prepareStatement(preparedQuery);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			ResultSet rs = preparedStatement.executeQuery();
			List<T> results = extractListFromResultSet(rs);
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
			if(logSqlQueries) logger.info(preparedQuery);
			PreparedStatement preparedStatement = getConnection().prepareStatement(preparedQuery);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			ResultSet rs = preparedStatement.executeQuery();
			return extractListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T updateOne(T object) {
		try {
			Table table = database.getTableForEntityClass(object.getClass());
			Field field = table.getId().getField();
			field.setAccessible(true);
			Object idOfOriginalObject = field.get(object);
			T original = findOne(idOfOriginalObject);
			List<Column> modifiedColumns = new ArrayList<>();
			for (Column column : table.getColumns()) {
				field = column.getField();
				if (field.get(original) == null && field.get(object) != null) {
					modifiedColumns.add(column);
					continue;
				}
				if (field.get(original) == null || field.get(object) == null)
					continue;
				if (!field.get(original).equals(field.get(object))) {
					modifiedColumns.add(column);
				}
			}

			// TODO: Smisliti bolji nacin
			String set = "";
			for (Column column : modifiedColumns) {
				set = set + column.getNameInDb() + " = ?, ";
			}
			set = set.substring(0, set.length()-2);

			String updateQuery = "UPDATE " + table.getName() + " SET " + set + " WHERE id = ?";
			if(logSqlQueries) logger.info(updateQuery);
			PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery);
			int idx = 1;
			for (Column column : modifiedColumns) {
				field = column.getField();
				preparedStatement.setObject(idx, field.get(object));
				idx++;
			}
			preparedStatement.setObject(idx, idOfOriginalObject);
			preparedStatement.execute();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Page<T> findAll(PageRequest pageRequest) {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			String query = String.format("SELECT SQL_CALC_FOUND_ROWS * FROM %s LIMIT ? OFFSET ?;", table.getName());
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatementResults = getConnection().prepareStatement(query);
			preparedStatementResults.setInt(1, pageRequest.getSize());
			preparedStatementResults.setInt(2, pageRequest.getSize() * pageRequest.getPage());
			ResultSet rs = preparedStatementResults.executeQuery();
			List<T> pageContent = extractListFromResultSet(rs);
			PreparedStatement preparedStatementTotalRows = getConnection().prepareStatement("SELECT FOUND_ROWS()");
			ResultSet rsTotalRows = preparedStatementTotalRows.executeQuery();
			Integer totalElements = 0;
			if(rsTotalRows.next()) totalElements = rsTotalRows.getInt(1);
			Double totalPages = totalElements / (double) pageRequest.getSize();
			return new PageImplementation<>(pageContent, pageRequest.getPage(), pageRequest.getSize(), pageContent.size(), (int) Math.ceil(totalPages), totalElements, (pageRequest.getPage() == 0), (pageRequest.getPage().equals((int)Math.ceil(totalPages)-1)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Connection getConnection() {
		// TODO: Trenutak u kom ce se od pula konekcija zatraziti konekcija
		return this.connection;
	}

	@Override
	public Boolean deleteAll() {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			String query = String.format("DELETE FROM %s WHERE %s > 0", table.getName(), table.getId().getNameInDb());
			if(logSqlQueries) logger.info(query);
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<T> findById(Object id) {
		T object = findOne(id);
		return Optional.ofNullable(object); 
	}

	@Override
	public Optional<List<T>> findBySpecification(Specification specification) {
		try {
			Table table = this.database.getTableForEntityClass(entity);
			SpecificationQueryGenerator specificationQueryGenerator = new SpecificationQueryGeneratorImpl();
			GeneratedResult generatedResult = specificationQueryGenerator.generateQuery(specification, table);
			if(logSqlQueries) logger.info(generatedResult.getQuery());
			PreparedStatement preparedStatement = getConnection().prepareStatement(generatedResult.getQuery());
			int idx = 1;
			for(Object arg: generatedResult.getArgs()) {
				preparedStatement.setObject(idx, arg);
				idx++;
			}
			ResultSet rs = preparedStatement.executeQuery();
			return Optional.ofNullable(extractListFromResultSet(rs));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Page<T>> findBySpecification(Specification specification, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
