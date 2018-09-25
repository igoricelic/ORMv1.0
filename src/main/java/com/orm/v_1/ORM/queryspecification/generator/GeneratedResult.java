package com.orm.v_1.ORM.queryspecification.generator;

import java.util.List;

public class GeneratedResult {
	
	private String query;
	
	private List<Object> args;
	
	public GeneratedResult(String query, List<Object> args) {
		this.query = query;
		this.args = args;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public List<Object> getArgs() {
		return args;
	}
	
	public void setArgs(List<Object> args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return "GeneratedResult [query=" + query + ", args=" + args + "]";
	}

}
