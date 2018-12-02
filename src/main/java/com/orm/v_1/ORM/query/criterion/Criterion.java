package com.orm.v_1.ORM.query.criterion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.query.criterion.enums.ComparationOperator;

@Deprecated
public class Criterion implements CriterionModel {
	private Class<?> entity;
	private Database databaseModel;
	private String fieldName;
	private ComparationOperator operator;
	private Object value;
	private DateFormat dateFormatter;

	public Criterion(String fieldName, Object value, ComparationOperator operator) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
		this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Override
	public String getQuery() {
		String nameInDb = databaseModel.getTableForEntityClass(entity).columnNameInDatabase(this.fieldName);
		String strValue = null;
		if(value.getClass() == String.class) strValue = "\""+value.toString()+"\"";
		else if(value.getClass() == Date.class) strValue = "\""+dateFormatter.format(value)+"\"";
		else strValue = value.toString();
		StringBuilder sb = new StringBuilder(10);
		sb.append(" `").append(nameInDb).append("`").append(ComparationOperator.getValueForQuery(operator))
		.append(strValue);
		return sb.toString();
	}

	@Override
	public void setDbModel(Database model, Class<?> entity) {
		this.databaseModel = model;
		this.entity = entity;
	}

}
