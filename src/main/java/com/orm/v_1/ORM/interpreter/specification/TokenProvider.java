package com.orm.v_1.ORM.interpreter.specification;

import java.util.Arrays;
import java.util.List;

import com.orm.v_1.ORM.interpreter.specification.enums.TokenType;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Table;

public class TokenProvider {
	
	public List<Token> keywords = Arrays.asList(
			
			new Token("FIND", TokenType.RESERVED_KEYWORDS, "SELECT *"),
			
			new Token("COUNT", TokenType.RESERVED_KEYWORDS, "SELECT COUNT(*)"),
			
			new Token("BY", TokenType.RESERVED_KEYWORDS, " WHERE"),
			
			new Token("AND", TokenType.RESERVED_KEYWORDS, " AND"),
			
			new Token("OR", TokenType.RESERVED_KEYWORDS, " OR"),
			
			new Token("EQ", TokenType.RESERVED_KEYWORDS, " = ?"),
			
			new Token("LT", TokenType.RESERVED_KEYWORDS, " < ?"),
			
			new Token("LTE", TokenType.RESERVED_KEYWORDS, " <= ?"),
			
			new Token("GT", TokenType.RESERVED_KEYWORDS, " > ?"),
			
			new Token("GTE", TokenType.RESERVED_KEYWORDS, " >= ?"),
			
			new Token("NE", TokenType.RESERVED_KEYWORDS, " != ?"),
			
			new Token("BEFORE", TokenType.RESERVED_KEYWORDS, " < ?"),
			 
			new Token("AFTER", TokenType.RESERVED_KEYWORDS, " > ?"),
			
			new Token("BETWEEN", TokenType.RESERVED_KEYWORDS, " BETWEEN (?, ?)"),
			
			new Token("ORDER", TokenType.RESERVED_KEYWORDS, " ORDER BY"),
			
			new Token("LIMIT", TokenType.RESERVED_KEYWORDS, " LIMIT ?"),
			
			new Token("STARTWITH", TokenType.RESERVED_KEYWORDS, " LIKE ?"),
			
			new Token("ENDWITH", TokenType.RESERVED_KEYWORDS, " LIKE ?"),
			
			new Token("CONTAINS", TokenType.RESERVED_KEYWORDS, " LIKE ?")
			
			);
	
	private Table table;
	
	public TokenProvider(Table table) {
		this.table = table;
	}
	
	public Token find (String input) {
		for(Token token: keywords) {
			if(token.equals(input)) {
				return token;
			}
		}
		Column column = table.getColumnByModelName(input);
		if(column != null) {
			return new Token(input, TokenType.VARIABLE, " "+column.getNameInDb());
		}
		return null;
	}
	
	public Token getFromToken () {
		return new Token("FROM", TokenType.RESERVED_KEYWORDS, " FROM " + table.getName());
	}

}
