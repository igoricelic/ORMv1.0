package com.orm.v_1.ORM.logic.impl;

import java.sql.Connection;

import com.orm.v_1.ORM.dynamic.DaoProxy;
import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.logic.repositories.DaoRepository;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.Table;

public class DatabaseDaoFactoryImpl implements DatabaseDaoFactory {
	
	private Connection connection;
	
	private Database database;
	
	private Boolean logSqlQueries;
	
	public DatabaseDaoFactoryImpl(Connection connection, Database database, Boolean logSqlQueries) {
		this.connection = connection;
		this.database = database;
		this.logSqlQueries = logSqlQueries;
	}

	@Override
	public <T> DaoRepository<T> buildDao(Class<T> clazz) throws InstantiationException, IllegalAccessException, NotSuportTypeException {
		if(database.getTableForEntityClass(clazz) == null) throw new NotSuportTypeException("Not suport DAO type!");
		return new DatabaseDaoImplementation<T>(clazz, connection, database, logSqlQueries);
	}

	@Override
	public <T> T generateProxy(Class<?> interfaceClass, Class<T> entityClass) {
		Table table = database.getTableForEntityClass(entityClass);
		return (T) DaoProxy.newInstance(new Class[]{ interfaceClass }, table, entityClass, new DatabaseDaoImplementation<>(entityClass, connection, database, logSqlQueries));
	}

}
