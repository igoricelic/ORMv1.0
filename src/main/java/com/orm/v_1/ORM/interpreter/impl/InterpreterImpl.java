package com.orm.v_1.ORM.interpreter.impl;

import java.lang.reflect.Method;
import java.util.List;

import com.orm.v_1.ORM.interpreter.Interpreter;
import com.orm.v_1.ORM.interpreter.LexicalAnalyzer;
import com.orm.v_1.ORM.interpreter.SemanticAnalyzer;
import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;
import com.orm.v_1.ORM.interpreter.specification.Token;
import com.orm.v_1.ORM.model.Table;

public class InterpreterImpl implements Interpreter {
	
	private LexicalAnalyzer lexicalAnalyzer;
	
	private SemanticAnalyzer semanticAnalyzer;
	
	public InterpreterImpl(Table table) {
		this.lexicalAnalyzer = new LexicalAnalyzerImpl(table);
		this.semanticAnalyzer = new SemanticAnalyzerImpl();
	}

	@Override
	public String buildStatement(Method method) throws InvalidTokenException {
		List<Token> tokens = this.lexicalAnalyzer.build(method.getName());
		String query = semanticAnalyzer.build(tokens);
		return query;
	}

}
