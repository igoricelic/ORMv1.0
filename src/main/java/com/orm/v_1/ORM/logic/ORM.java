package com.orm.v_1.ORM.logic;

import java.sql.SQLException;
import java.util.List;

import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.exceptions.NotCompatibleTypesException;
import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.exceptions.TableNotFoundException;

public interface ORM {
	
	public DatabaseDaoFactory generateMapping (String databaseName, List<Class<?>> entities, boolean createDatabase) throws NoSuchFieldException, SecurityException, SQLException, ClassNotFoundException, TableNotFoundException, NotSuportTypeException, ColumnNotFoundException, NotCompatibleTypesException;
	
}
