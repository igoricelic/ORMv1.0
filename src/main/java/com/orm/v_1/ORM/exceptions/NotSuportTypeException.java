package com.orm.v_1.ORM.exceptions;

public class NotSuportTypeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public NotSuportTypeException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
