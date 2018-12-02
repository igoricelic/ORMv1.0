package com.orm.v_1.ORM.query;

import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.query.criterion.CriterionModel;

@Deprecated
public class Query {
	private Database db;
	private Class<?> entity;
	private CriterionModel criterions;
	private int limit;
	private Object orderBy;
	private boolean reverse;
	
	public Query(CriterionModel criterions) {
		this.criterions = criterions;
		reverse = false;
		this.orderBy = null;
		this.limit = -1;
	}
	
	public Query() {
		reverse = false;
		this.orderBy = null;
		this.limit = -1;
	}
	
	public void setDb(Database db, Class<?> entity) {
		this.db = db;
		this.entity = entity;
		if(criterions!=null) this.criterions.setDbModel(db, entity);
	}
	
	public String getQuery () {
		StringBuilder sb = new StringBuilder(30);
		sb.append("SELECT * FROM `").append(db.getTableForEntityClass(entity).getName()).append("`");
		if(criterions!=null) {
			sb.append(" WHERE").append(criterions.getQuery());
		}
		if(orderBy!=null) {
			String reverseStr = (reverse) ? "-" : "";
			String columnName = db.getTableForEntityClass(entity).columnNameInDatabase(orderBy.toString());
			sb.append(" order by ").append(reverseStr).append(columnName);
		}
		if(limit!=-1) {
			sb.append(" limit ").append(limit);
		}
		sb.append(";");
		return sb.toString();
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void setOrderBy(Object orderBy) {
		this.orderBy = orderBy;
	}
	
	public void setOrderBy(Object orderBy, boolean reverse) {
		this.reverse = reverse;
		this.setOrderBy(orderBy);
	}

}
