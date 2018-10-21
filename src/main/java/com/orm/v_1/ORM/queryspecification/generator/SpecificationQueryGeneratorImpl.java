package com.orm.v_1.ORM.queryspecification.generator;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.enums.ColumnType;
import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.exceptions.NotCompatibleTypesException;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class SpecificationQueryGeneratorImpl implements SpecificationQueryGenerator {

	
	
	@Override
	public GeneratedResult generateQuery(Specification specification, Table table) throws ColumnNotFoundException, NotCompatibleTypesException{
		StringBuilder query = new StringBuilder(300);
		List<Object> args = new ArrayList<>();
		query.append("SELECT * FROM %s ");
		for(Condition condition: specification.getConditions()) {
			if(condition.getOperator() == null) {
				query.append("WHERE ");
			} else {
				query.append(condition.getOperator().getStringRepresentation());
			}
			Column column = table.getColumnByModelName(condition.getField());
			if(column == null) throw new ColumnNotFoundException("Field " + condition.getField() + " not found in domain class!");
			if(!ColumnType.getType(column.getType()).equals(condition.getValue().getClass())) throw new NotCompatibleTypesException("Not compatible types in field " + condition.getField() + ". Expected type is " + ColumnType.getType(column.getType()) + " but found type is " + condition.getValue().getClass());
			query.append(column.getNameInDb()).append(condition.getComparator().getStringRepresentation());
			args.add(condition.getValue());
		}
		// TODO: Ostala nadgradnja za Order i Limit
		return new GeneratedResult(String.format(query.toString(), table.getName()), args);
	}

}
