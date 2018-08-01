package com.orm.v_1.ORM.logic;

import com.orm.v_1.ORM.exceptions.NotSuportTypeException;

public interface DatabaseDaoFactory {
	
	public <T> DatabaseDao<T> buildDao (Class<T> clazz) throws InstantiationException, IllegalAccessException, NotSuportTypeException;

}
