package com.orm.v_1.ORM.queryspecification.enums;

public enum Operator {
	
	AND,
	
	OR,
	
	NOT;
	
	public String getStringRepresentation () {
		switch (this) {
			case AND:
				return "AND ";
			case OR:
				return "OR ";
			case NOT:
				return "NOT ";
			default:
				break;
		}
		return null;
	}

}
