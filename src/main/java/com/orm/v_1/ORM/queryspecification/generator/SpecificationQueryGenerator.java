package com.orm.v_1.ORM.queryspecification.generator;

import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.exceptions.NotCompatibleTypesException;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.queryspecification.Specification;

public interface SpecificationQueryGenerator {
	
	public GeneratedResult generateQuery (Specification specification, Table table) throws ColumnNotFoundException, NotCompatibleTypesException;

}
