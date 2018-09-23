package com.orm.v_1.ORM.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.orm.v_1.ORM.exceptions.OrmConfigurationException;

public class ConfigParser {
	
	private String HOST_VALUE, USN_VALUE, PASS_VALUE, NAME_VALUE, PACKAGE_PATH_VALUE;
	
	private Integer PORT_VALUE;
	
	private Boolean CREATE_TABLES_VALUE = false, SHOW_SQL_VALUE = false;
	
	private List<Class<?>> ENTITIES;
	
	private String HOST_KEY = "DB_HOST", USN_KEY = "DB_USERNAME", PASS_KEY = "DB_PASSWORD", NAME_KEY = "DB_NAME", PATH_KEY = "ENTITIES";
	private String PORT_KEY = "DB_PORT", CREATE_TABLES_KEY = "CREATE_TABLES", SHOW_SQL_KEY = "SHOW_SQL";

	private Scanner scanner;
	
	public ConfigParser(File file) throws OrmConfigurationException {
		scanner = null;
		parse(file);
	}
	
	private Boolean parse (File file) throws OrmConfigurationException {
		try {
			scanner = new Scanner(file);
			Map<String, String> map = new HashMap<>();
			
			while(scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split("=");
				if(tokens.length != 2) throw new OrmConfigurationException("Not valid format of file!");
				map.put(tokens[0], tokens[1]);
			}
			scanner.close();
			
			if(map.get(HOST_KEY) == null) throw new OrmConfigurationException("Not present value for HOST!");
			HOST_VALUE = map.get(HOST_KEY);
			
			if(map.get(USN_KEY) == null) throw new OrmConfigurationException("Not present value for USERNAME!");
			USN_VALUE = map.get(USN_KEY);
			
			if(map.get(PASS_KEY) == null) PASS_VALUE = "";
			else PASS_VALUE = map.get(PASS_KEY);
			
			if(map.get(NAME_KEY) == null) throw new OrmConfigurationException("Not present value for NAME of DATABASE!");
			NAME_VALUE = map.get(NAME_KEY);
			
			if(map.get(PATH_KEY) == null) throw new OrmConfigurationException("Not present path to ENTITY classes!");
			PACKAGE_PATH_VALUE = map.get(PATH_KEY);
			ENTITIES = findClassiesByPackagePath(PACKAGE_PATH_VALUE);
			
			if(map.get(PORT_KEY) == null) throw new OrmConfigurationException("Not present value for PORT!");
			PORT_VALUE = Integer.parseInt(map.get(PORT_KEY));
			
			if(map.get(CREATE_TABLES_KEY) == null) CREATE_TABLES_VALUE = false;
			else CREATE_TABLES_VALUE = Boolean.parseBoolean(map.get(CREATE_TABLES_KEY));
			
			if(map.get(SHOW_SQL_KEY) == null) SHOW_SQL_VALUE = false;
			else SHOW_SQL_VALUE = Boolean.parseBoolean(map.get(SHOW_SQL_KEY));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private List<Class<?>> findClassiesByPackagePath (String PATH) throws OrmConfigurationException {
		List<Class<?>> ENTITIES = new ArrayList<>();
		
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(PATH))));

		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
		if(classes.size() == 0) throw new OrmConfigurationException("Classes not found in package with path " + PATH);
		
		ENTITIES.addAll(classes);
		return ENTITIES;
	}
	
	public String getHost () {
		return HOST_VALUE;
	}
	
	public String getDbName () {
		return NAME_VALUE;
	}
	
	public String getUsername () {
		return USN_VALUE;
	}
	
	public String getPassword () {
		return PASS_VALUE;
	}
	
	public Integer getPort () {
		return PORT_VALUE;
	}
	
	public List<Class<?>> getEntites () {
		return ENTITIES;
	}
	
	public Boolean isCreateTable () {
		return CREATE_TABLES_VALUE;
	}
	
	public Boolean isShowSql () {
		return SHOW_SQL_VALUE;
	}

}
