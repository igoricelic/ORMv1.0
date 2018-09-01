package com.orm.v_1.ORM.interpreter;

import java.util.List;

import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;
import com.orm.v_1.ORM.interpreter.specification.Token;

public interface LexicalAnalyzer {
	
	public List<Token> build (String input) throws InvalidTokenException;

}
