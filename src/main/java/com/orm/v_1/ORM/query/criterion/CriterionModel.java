package com.orm.v_1.ORM.query.criterion;

import com.orm.v_1.ORM.model.Database;

@Deprecated
public interface CriterionModel {
	
	public void setDbModel (Database model, Class<?> entity);
	
	public String getQuery ();

}
