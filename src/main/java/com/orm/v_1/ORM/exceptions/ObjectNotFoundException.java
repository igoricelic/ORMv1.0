package com.orm.v_1.ORM.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ObjectNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
