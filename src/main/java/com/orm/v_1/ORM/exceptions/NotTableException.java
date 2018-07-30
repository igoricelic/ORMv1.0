package com.orm.v_1.ORM.exceptions;

public class NotTableException extends Exception {
	
	public NotTableException() {
		super("Tabale doesn't exist in database!");
	}

}
