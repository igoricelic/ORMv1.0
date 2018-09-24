package com.orm.v_1.ORM.queryspecification.builder;

import java.util.Observable;

import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.enums.Operator;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class MoreConditionsBuilder extends Observable {
	
	private SpecificationBuilder specificationBuilder;
	
	public MoreConditionsBuilder(SpecificationBuilder specificationBuilder) {
		this.specificationBuilder = specificationBuilder;
		addObserver(specificationBuilder);
	}
	
	public MoreConditionsBuilder addCondition (Operator operator, String field, Object value, Comparator comparator) {
		Condition condition = new Condition(operator, field, value, comparator);
		setChanged();
		notifyObservers(condition);
		return this;
	}
	
	public MoreConditionsBuilder limit (Integer limit) {
		this.specificationBuilder.limit(limit);
		return this;
	}
	
	public MoreConditionsBuilder orderBy (String field) {
		this.specificationBuilder.orderBy(field);
		return this;
	}
	
	public MoreConditionsBuilder orderBy (String field, Boolean reverse) {
		this.specificationBuilder.orderBy(field, reverse);
		return this;
	}
	
	public Specification build () {
		return this.specificationBuilder.build();
	}

}
