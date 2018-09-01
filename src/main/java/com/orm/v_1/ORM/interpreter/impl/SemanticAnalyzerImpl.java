package com.orm.v_1.ORM.interpreter.impl;

import java.util.List;

import com.orm.v_1.ORM.interpreter.SemanticAnalyzer;
import com.orm.v_1.ORM.interpreter.specification.Token;

public class SemanticAnalyzerImpl implements SemanticAnalyzer {
	
	public SemanticAnalyzerImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String build(List<Token> input) {
		StringBuilder queryBuilder = new StringBuilder(50);
		for(Token token: input) {
			queryBuilder.append(token.getRepresentation());
		}
		return queryBuilder.toString();
	}

}
