package com.orm.v_1.ORM.enums;

import java.util.Date;

public enum ColumnType {
	
	AUTO,
	VARCHAR,
	INT,
	DECIMAL,
	DATETIME,
	BLOB;
	
	public static Class<?> getType(ColumnType columnType) {
		switch (columnType) {
			case VARCHAR:
				return String.class;
			case INT:
				return Integer.class;
			case DECIMAL:
				return Double.class;
			case DATETIME:
				return Date.class;
			case BLOB:
				return Byte[].class;
			default:
				break;
		}
		return null;
	}
	
	public static ColumnType getTypeByClass (Class<?> clazz) {
		switch (clazz.getSimpleName()) {
			case "String":
				return ColumnType.VARCHAR;
			case "Integer":
				return ColumnType.INT;
			case "Double":
				return ColumnType.DECIMAL;
			case "Date":
				return ColumnType.DATETIME;
			default:
				break;
		}
		return null;
	}
	
	public static Integer getDefaultLengthByType (ColumnType type) {
		switch (type) {
			case VARCHAR:
				return 45;
			case INT:
				return 10;
			case DECIMAL:
				return 10;
			case DATETIME:
				return -1;
			case BLOB:
				return -1;
			default:
				break;
		}
		return null;
	}

}
