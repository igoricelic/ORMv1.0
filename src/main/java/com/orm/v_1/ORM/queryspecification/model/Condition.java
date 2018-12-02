package com.orm.v_1.ORM.queryspecification.model;

import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.enums.Operator;

public class Condition {
	
	private String field;
	
	private Object value;
	
	private Comparator comparator;
	
	private Operator operator;
	
	public Condition(String field, Object value, Comparator comparator) {
		if (comparator == Comparator.Contains) {
			if(value instanceof String) {
				value = (String) "%" + value + "%";
			}
		}
		this.field = field;
		this.value = value;
		this.comparator = comparator;
	}
	
	public Condition(Operator operator, String field, Object value, Comparator comparator) {
		this(field, value, comparator);
		this.operator = operator;
	}
	
	public String getField() {
		return field;
	}
	
	public Object getValue() {
		return value;
	}
	
	public Comparator getComparator() {
		return comparator;
	}
	
	public Operator getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		return "Condition [field=" + field + ", value=" + value + ", comparator=" + comparator + ", operator="
				+ operator + "]";
	}
	
}
