package com.orm.v_1.ORM.query.generator;

import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.model.Table;

@Deprecated
public interface QueryGenerator {
	
	public String generateQuery (String specification, Table table, Object[] args) throws ColumnNotFoundException;

}
