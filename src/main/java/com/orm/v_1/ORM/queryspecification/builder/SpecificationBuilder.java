package com.orm.v_1.ORM.queryspecification.builder;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class SpecificationBuilder implements Observer {
	
	private List<Condition> conditions;
	
	private Integer limit;
	
	private String orderByColumn;
	
	private Boolean reverse;
	
	public SpecificationBuilder() {
		this.reverse = false;
		this.conditions = new ArrayList<>();
	}
	
	public MoreConditionsBuilder addCondition (String field, Object value, Comparator comparator) {
		Condition condition = new Condition(field, value, comparator);
		this.conditions.add(condition);
		return new MoreConditionsBuilder(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Condition) {
			Condition condition = (Condition) arg;
			this.conditions.add(condition);
		}
	}
	
	public SpecificationBuilder limit (Integer limit) {
		this.limit = limit;
		return this;
	}
	
	public SpecificationBuilder orderBy (String field) {
		this.orderByColumn = field;
		return this;
	}
	
	public SpecificationBuilder orderBy (String field, Boolean reverse) {
		this.orderByColumn = field;
		this.reverse = reverse;
		return this;
	}
	
	public Specification build () {
		//Constructor<?> constructor = Specification.class.getConstructors();
		System.out.println(toString());
		return null;
	}

	@Override
	public String toString() {
		return "SpecificationBuilder [conditions=" + conditions + ", limit=" + limit + ", orderByColumn="
				+ orderByColumn + ", reverse=" + reverse + "]";
	}

}
