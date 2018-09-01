package com.orm.v_1.ORM.interpreter;

import java.lang.reflect.Method;

import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;

public interface Interpreter {
	
	public String buildStatement (Method method) throws InvalidTokenException;

}
