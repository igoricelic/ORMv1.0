package com.orm.v_1.ORM.logic.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;

import com.orm.v_1.ORM.dynamic.DaoProxy;
import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.Table;

public class DatabaseDaoFactoryImpl implements DatabaseDaoFactory {
	
	private Connection connection;
	
	private Database database;
	
	public DatabaseDaoFactoryImpl(Connection connection, Database database) {
		this.connection = connection;
		this.database = database;
	}

	@Override
	public <T> DatabaseDao<T> buildDao(Class<T> clazz) throws InstantiationException, IllegalAccessException, NotSuportTypeException {
		if(database.getTableForEntityClass(clazz) == null) throw new NotSuportTypeException("Not suport DAO type!");
		return new DatabaseDaoImplementation<T>(clazz, connection, database);
	}

	@Override
	public <T> T generateProxy(Class<?> interfaceClass, Class<T> entityClass) {
		try {
			Table table = database.getTableForEntityClass(entityClass);
			return (T) DaoProxy.newInstance(new Class[]{ interfaceClass }, table, buildDao(entityClass));
		} catch (InstantiationException | IllegalAccessException | NotSuportTypeException e) {
			e.printStackTrace();
		}
		return null;
	}

}
