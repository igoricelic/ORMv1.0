package com.orm.v_1.ORM.model;

import com.orm.v_1.ORM.enums.ColumnType;

public class Column {
	
	private String nameInModel;
	
	private String nameInDb;
	
	private ColumnType type;
	
	private int length;
	
	private boolean notNull;
	
	private boolean primaryKey;
	
	private boolean unique;

	public Column(String nameInModel, String nameInDb, ColumnType type, int length, boolean notNull, boolean primaryKey, boolean unique) {
		super();
		this.nameInModel = nameInModel;
		this.nameInDb = nameInDb;
		this.type = type;
		this.length = length;
		this.notNull = notNull;
		this.primaryKey = primaryKey;
		this.unique = unique;
	}

	public String getNameInModel() {
		return nameInModel;
	}
	
	public String getNameInDb() {
		return nameInDb;
	}

	public ColumnType getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public boolean isNotNull() {
		return notNull;
	}
	
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	
	public boolean isUnique() {
		return unique;
	}
	
	public String createQuery () {
		String sufix = (isNotNull()) ? "NOT NULL " : "";
		sufix = (isUnique()) ? (sufix + "UNIQUE,") : (sufix + ",");
		StringBuilder sb = new StringBuilder(30);
		sb.append("`").append(this.nameInDb)
		.append("` ").append(this.type.toString());
		int len = length;
		if(len <= 0) {
			len = ColumnType.getDefaultLengthByType(getType());
		}
		if(len > 0) sb.append("(").append(len).append(")");
		sb.append(" "+sufix);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Column) {
			Column anotherColumn = (Column) obj;
			if(anotherColumn.nameInDb.equals(nameInDb)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(10);
		return sb.append(" Column name in model: ").append(this.nameInModel).append(" Name in db: ")
				.append(this.nameInDb).append(" Length: ").append(this.length).append(", Not null: ")
				.append(notNull).toString();
	}

}
