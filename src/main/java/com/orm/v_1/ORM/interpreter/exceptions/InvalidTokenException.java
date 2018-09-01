package com.orm.v_1.ORM.interpreter.exceptions;

public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InvalidTokenException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
