package com.orm.v_1.ORM.queryspecification;

import java.util.List;

import com.orm.v_1.ORM.queryspecification.builder.SpecificationBuilder;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class Specification {

	private List<Condition> conditions;

	private Integer limit;

	private String orderByColumn;

	private Boolean reverse;
	
	private Specification(List<Condition> conditions, Integer limit, String orderByColumn, Boolean reverse) {
		this.conditions = conditions;
		this.limit = limit;
		this.orderByColumn = orderByColumn;
		this.reverse = reverse;
	}
	
	public List<Condition> getConditions() {
		return conditions;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public String getOrderByColumn() {
		return orderByColumn;
	}
	
	public Boolean getReverse() {
		return reverse;
	}
	
	public static SpecificationBuilder builder () {
		return new SpecificationBuilder();
	}

	@Override
	public String toString() {
		return "Specification [conditions=" + conditions + ", limit=" + limit + ", orderByColumn=" + orderByColumn
				+ ", reverse=" + reverse + "]";
	}

}
