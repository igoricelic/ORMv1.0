package com.orm.v_1.ORM.queryspecification;

import java.util.List;

import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;
import com.orm.v_1.ORM.queryspecification.builder.SpecificationBuilder;
import com.orm.v_1.ORM.queryspecification.dsl.DslProviderService;
import com.orm.v_1.ORM.queryspecification.dsl.DslProviderServiceImpl;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class Specification {

	private List<Condition> conditions;

	private Integer limit;

	private String orderByColumn;

	private Boolean reverse;
	
	private DslProviderService dslProviderService;
	
	private Specification(List<Condition> conditions, Integer limit, String orderByColumn, Boolean reverse) {
		this.conditions = conditions;
		this.limit = limit;
		this.orderByColumn = orderByColumn;
		this.reverse = reverse;
	}
	
	private Specification(List<Condition> conditions) {
		this.conditions = conditions;
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
	
	public static Specification buildByDslQuery (String dslQuery) {
		try {
			DslProviderService dslProviderService = new DslProviderServiceImpl();
			return dslProviderService.provide(dslQuery);
		} catch (InvalidTokenException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return "Specification [conditions=" + conditions + ", limit=" + limit + ", orderByColumn=" + orderByColumn
				+ ", reverse=" + reverse + "]";
	}

}
