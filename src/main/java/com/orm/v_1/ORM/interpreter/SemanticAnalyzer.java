package com.orm.v_1.ORM.interpreter;

import java.util.List;

import com.orm.v_1.ORM.interpreter.specification.Token;

public interface SemanticAnalyzer {
	
	public String build (List<Token> input);

}
