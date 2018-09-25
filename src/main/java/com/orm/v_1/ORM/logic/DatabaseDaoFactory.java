package com.orm.v_1.ORM.logic;

import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.logic.repositories.CrudRepository;

public interface DatabaseDaoFactory {
	
	public <T> T buildDao (Class<T> clazz) throws InstantiationException, IllegalAccessException, NotSuportTypeException;

	public <T> T generateProxy (Class<?> interfaceClass, Class<T> entityClass);
	
}
