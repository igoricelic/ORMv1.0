package com.orm.v_1.ORM.query.criterion.enums;

public enum ComparationOperator {
	Equality,
	LessThan,
	LessThanEquality,
	GreaterThan,
	GreaterThanEquality,
	NotEquals,
	Like,
	Before,
	After;
	
	public static String getValueForQuery (ComparationOperator operator) {
		switch (operator) {
			case Equality:
				return " = ";
			case LessThan:
				return " < ";
			case LessThanEquality:
				return " <= ";
			case GreaterThan:
				return " > ";
			case GreaterThanEquality:
				return " >= ";
			case NotEquals:
				return " != ";
			case Like:
				return " like ";
			case Before:
				return " before ";
			case After:
				return " after ";
			default:
				break;
		}
			
		return null;
	}
	
}
