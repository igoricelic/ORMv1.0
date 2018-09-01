package com.orm.v_1.ORM.interpreter.specification;

import com.orm.v_1.ORM.interpreter.specification.enums.TokenType;

public class Token {
	
	private String name;
	
	private TokenType type;
	
	private String representation;
	
	public Token(String name, TokenType type, String representation) {
		this.name = name;
		this.type = type;
		this.representation = representation;
	}
	
	public String getName() {
		return name;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getRepresentation() {
		return representation;
	}
	
	@Override
	public boolean equals(Object name) {
		if(name instanceof String) {
			String tokenName = (String) name;
			if(this.name.equalsIgnoreCase(tokenName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Token [name=" + name + ", type=" + type + ", representation=" + representation + "]";
	}

}
