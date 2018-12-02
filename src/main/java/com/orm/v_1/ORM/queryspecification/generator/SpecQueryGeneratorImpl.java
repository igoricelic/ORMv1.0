package com.orm.v_1.ORM.queryspecification.generator;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.exceptions.NotCompatibleTypesException;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class SpecQueryGeneratorImpl implements SpecificationQueryGenerator {

	@Override
	public GeneratedResult generateQuery(Specification specification, Table table, QueryType queryType)
			throws ColumnNotFoundException, NotCompatibleTypesException {
		GeneratedResult generatedResult = generateConditionExpression(specification, table);
		String conditionsQuery = generatedResult.getQuery();

		String fullQuery = "SELECT %s FROM %s %s";
		
		String returnTypeAsString = null;
		switch (queryType) {
		case SELECT:
			returnTypeAsString = "*";
			break;
		case COUNT:
			returnTypeAsString = "COUNT(*) as count";
			break;
		case EXISTS:
			returnTypeAsString = "COUNT(*) as count";
			break;
		default:
			break;
		}
		fullQuery = String.format(fullQuery, returnTypeAsString, table.getName(), (!conditionsQuery.equals("") ? "WHERE " + conditionsQuery + ";" : ";"));
		generatedResult.setQuery(fullQuery);
		System.out.println(fullQuery);
		return generatedResult;
	}

	private GeneratedResult generateConditionExpression(Specification specification, Table table)
			throws ColumnNotFoundException, NotCompatibleTypesException {
		StringBuilder queryAsString = new StringBuilder(100);
		List<Object> args = new ArrayList<>();

		for (Condition condition : specification.getConditions()) {
			Column column = table.getColumnByModelName(condition.getField());
			if (column == null)
				throw new ColumnNotFoundException(String.format("Column with name %s not found in table %s!",
						condition.getField(), table.getName()));
			if (!column.getClazz().equals(condition.getValue().getClass()))
				throw new NotCompatibleTypesException(
						String.format("Not compatible types! Expected type for field %s is %s!",
								column.getNameInModel(), column.getClazz().getSimpleName()));
			queryAsString
					.append((condition.getOperator() != null) ? condition.getOperator().getStringRepresentation() : "")
					.append(column.getNameInDb()).append(condition.getComparator().getStringRepresentation());
			args.add(condition.getValue());
		}

		return new GeneratedResult(queryAsString.toString(), args);
	}

}
