package com.orm.v_1.ORM.exceptions;

public class TableNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public TableNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
