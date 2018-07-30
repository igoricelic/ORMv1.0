package com.orm.v_1.ORM.logic;

import java.util.List;

import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.model.Database;

public interface ModelBuilderService {
	
	public Database buildModel(String databaseName, List<Class<?>> entities) throws NoSuchFieldException, SecurityException, NotSuportTypeException;

}
