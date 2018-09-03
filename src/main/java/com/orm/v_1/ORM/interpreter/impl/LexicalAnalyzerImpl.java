package com.orm.v_1.ORM.interpreter.impl;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.interpreter.LexicalAnalyzer;
import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;
import com.orm.v_1.ORM.interpreter.specification.Token;
import com.orm.v_1.ORM.interpreter.specification.TokenProvider;
import com.orm.v_1.ORM.model.Table;

public class LexicalAnalyzerImpl implements LexicalAnalyzer {
	
	private static final Integer FROM_TOKEN_POSITION = 1;
	
	private TokenProvider tokenProvider;
	
	public LexicalAnalyzerImpl(Table table) {
		this.tokenProvider = new TokenProvider(table);
	}

	@Override
	public List<Token> build(String input)  throws InvalidTokenException {
		List<Token> tokens = new ArrayList<>();
		Token currToken = null, potentialToken = null;
		StringBuilder tokenBuilder = new StringBuilder(100);
		int index = 0;
		do {
			for(int i=index; i<input.length(); i++) {
				tokenBuilder.append(input.charAt(i));
				potentialToken = tokenProvider.find(tokenBuilder.toString());
				if(potentialToken != null) {
					currToken = potentialToken;
					index = i + 1;
				}
			}
			if(currToken == null) throw new InvalidTokenException("Invalid token "+input.substring(index));
			tokens.add(currToken);
			currToken = null;
			tokenBuilder.setLength(0);
		} while(index < input.length());
		// take FROM token (from -table_name-) from TokenProvider and put it on second place
		tokens.add(FROM_TOKEN_POSITION, tokenProvider.getFromToken());
		return tokens;
	}

}
