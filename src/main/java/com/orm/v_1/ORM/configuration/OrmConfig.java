package com.orm.v_1.ORM.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.exceptions.NotCompatibleTypesException;
import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.exceptions.OrmConfigurationException;
import com.orm.v_1.ORM.exceptions.TableNotFoundException;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.logic.ORM;
import com.orm.v_1.ORM.logic.impl.OrmServiceImpl;
public class OrmConfig {
	
	private static DatabaseDaoFactory daoFactory;
	
	private OrmConfig(File configFile) throws OrmConfigurationException {
		try {
			ConfigParser configParser = new ConfigParser(configFile);
			ORM orm = new OrmServiceImpl(configParser.getHost(), configParser.getPort(), configParser.getUsername(), configParser.getPassword());
			daoFactory = orm.generateMapping(configParser.getDbName(), configParser.getEntites(), configParser.isCreateTable(), configParser.isShowSql());
		} catch (ClassNotFoundException | SQLException | NoSuchFieldException | SecurityException | TableNotFoundException | NotSuportTypeException | ColumnNotFoundException | NotCompatibleTypesException e) {
			e.printStackTrace();
		}
	}
	
	public static Boolean setConfiguration (String path) {
		try {
			File file = new File(path);
			if(!file.exists()) throw new FileNotFoundException();
			new OrmConfig(file);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static <T> T buildRepository (Class<T> clazz) throws OrmConfigurationException {
		try {
			return daoFactory.buildDao(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T buildProxy (Class<?> repositoryClass, Class<T> entityClass) throws OrmConfigurationException {
		try {
			return daoFactory.generateProxy(repositoryClass, entityClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
