package com.orm.v_1.ORM.exceptions;

public class NotCompatibleTypesException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public NotCompatibleTypesException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
