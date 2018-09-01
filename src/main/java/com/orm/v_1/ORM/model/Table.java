package com.orm.v_1.ORM.model;

import java.util.List;

public class Table {
	
	private String name;
	
	private Id id;
	
	private List<Column> columns;
	
	private List<ForeignKey> foreignKeys;
	
	public Table(String name, Id id, List<Column> columns, List<ForeignKey> foreignKeys) {
		this.name = name;
		this.id = id;
		this.columns = columns;
		this.foreignKeys = foreignKeys;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}
	
	public Id getId() {
		return id;
	}
	
	public String createQuery () {
		StringBuilder sb = new StringBuilder(50);
		sb.append("CREATE TABLE `").append(this.name).append("` (");
		for(Column column: columns) {
			sb.append(column.createQuery());
		}
		sb.append("PRIMARY KEY (`").append(this.id.getNameInDb()).append("`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		return sb.toString();
	}
	
	public String columnNameInDatabase (String columName) {
		for(Column c: columns) {
			if(c.getNameInModel().equals(columName)) return c.getNameInDb();
		}
		return null;
	}
	
	private String getNamesOfColumns () {
		StringBuilder sb = new StringBuilder(30);
		
		for(Column column: columns) {
			sb.append("`").append(column.getNameInDb()).append("`, ");
		}
		
		return sb.toString().substring(0, sb.toString().length()-2);
	}
	
	public Column getColumnByDbName (String nameInDb) {
		for(Column column: columns) {
			if(column.getNameInDb().equals(nameInDb)) {
				return column;
			}
		}
		return null;
	}
	
	public Column getColumnByModelName (String nameInModel) {
		for(Column column: columns) {
			if(column.getNameInModel().equalsIgnoreCase(nameInModel)) {
				return column;
			}
		}
		return null;
	}
	
	public String getInsertQuery () {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO `").append(this.name).append("` (").append(getNamesOfColumns()).append(") VALUES (%s)");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(20);
		return sb.append(" Table name: ").append(this.name).append(", Id: ")
				.append(this.id).append(", Columns: ").append(this.columns).toString();
	}

}
