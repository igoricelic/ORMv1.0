package com.orm.v_1.ORM.model;

import java.lang.reflect.Field;

import com.orm.v_1.ORM.enums.ColumnType;

public class Id extends Column {
	
	private boolean autoIncrement;

	public Id(String nameInModel, String nameInDb, Field field, int length, boolean autoIncrement) {
		super(nameInModel, nameInDb, ColumnType.INT, field, length, true, true, true);
		this.autoIncrement = autoIncrement;
	}
	
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	
	@Override
	public String createQuery() {
		String sufix = (isAutoIncrement()) ? "not NULL AUTO_INCREMENT," : "not NULL UNIQUE,";
		StringBuilder sb = new StringBuilder(30);
		sb.append("`").append(super.getNameInDb())
		.append("` ").append(super.getType().toString());
		int len = super.getLength();
		if(len <= 0) {
			len = ColumnType.getDefaultLengthByType(getType());
		}
		if(len > 0) sb.append("(").append(len).append(")");
		sb.append(" "+sufix);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(10);
		return sb.append(" ID ").append(super.getNameInDb()).append(" Auto increment: ")
				.append(autoIncrement).toString();
	}

}
