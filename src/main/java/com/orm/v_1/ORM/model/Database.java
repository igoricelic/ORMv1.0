package com.orm.v_1.ORM.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Database {
	
	private String name;
	
	private Map<Class<?>, Table> mapTables;
	
	public Database(String name, Map<Class<?>, Table> tables) {
		this.name = name;
		this.mapTables = tables;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection<Table> getTables() {
		return this.mapTables.values();
	}
	
	public Table getTableForEntityClass (Class<?> entityClass) {
		return this.mapTables.get(entityClass);
	}
	
	public List<String> createQuery () {
		List<String> queries = new ArrayList<>();
		for(Table table: getTables()) {
			queries.add("DROP TABLE IF EXISTS `" + table.getName() + "`");
			queries.add(table.createQuery());
		}
		return queries;
	}
	
	public boolean hasTable (String tableName) {
		for(Table table: getTables()) {
			if(table.getName().equals(tableName)) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getTableNames () {
		List<String> tableNames = new ArrayList<>();
		for(Table table: mapTables.values()) tableNames.add(table.getName());
		return tableNames;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(10);
		return sb.append("DB name: ").append(this.name).append(", Content: ")
				.append(this.mapTables).toString();
	}

}
