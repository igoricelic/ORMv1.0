package com.orm.v_1.ORM.logic.impl;

import java.sql.Connection;

import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.model.Database;

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
		return new DatabaseDaoImplementation<T>(clazz.newInstance(), connection, database);
	}

}
