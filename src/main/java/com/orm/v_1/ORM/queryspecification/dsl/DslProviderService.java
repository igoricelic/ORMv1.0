package com.orm.v_1.ORM.queryspecification.dsl;

import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;
import com.orm.v_1.ORM.queryspecification.Specification;

public interface DslProviderService {
	
	public Specification provide (String searchSpecificatin) throws InvalidTokenException;

}
