package com.orm.v_1.ORM.query.criterion;

import java.util.List;

import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.query.criterion.enums.QueryOperator;

@Deprecated
public class UnionCriteria implements CriterionModel {
	private List<CriterionModel> listCriterions;
	private QueryOperator operator;
	
	public UnionCriteria(List<CriterionModel> listCriterions, QueryOperator operator) {
		this.listCriterions = listCriterions;
		this.operator = operator;
	}

	@Override
	public String getQuery() {
		StringBuilder sb = new StringBuilder(50);
		sb.append(" (");
		for(CriterionModel c: listCriterions) {
			sb.append(c.getQuery()).append(" ").append(operator.toString());
		}
		String query = sb.toString();
		if(operator == QueryOperator.AND) query = query.substring(0, query.length()-4);
		else query = query.substring(0, query.length()-3);
		query = query + ")";
		return query;
	}

	@Override
	public void setDbModel(Database model, Class<?> entity) {
		for(CriterionModel c: listCriterions) c.setDbModel(model, entity);
	}

}
