package com.orm.v_1.ORM.queryspecification.enums;

public enum Comparator {

	Equality,
	
	LessThan,
	
	LessThanEquality,
	
	GreaterThan,
	
	GreaterThanEquality,
	
	NotEquals,
	
	StartsWith,
	
	EndsWith,
	
	Contains,
	
	Before,
	
	After;
	
	public String getStringRepresentation () {
		switch (this) {
			case Equality:
				return " = ? ";
			case LessThan:
				return " < ? ";
			case LessThanEquality:
				return " <= ? ";
			case GreaterThan:
				return " > ? ";
			case GreaterThanEquality:
				return " >= ? ";
			case NotEquals:
				return " != ?";
			case StartsWith:
				return " LIKE ? ";
			case EndsWith:
				return " LIKE ? ";
			case Contains:
				return " LIKE ? ";
			case Before:
				return " < ? ";
			case After:
				return " > ? ";
			default:
				break;
		}
		return null;
	}

}
