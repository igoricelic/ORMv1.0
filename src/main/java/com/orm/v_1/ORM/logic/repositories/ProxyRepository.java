package com.orm.v_1.ORM.logic.repositories;

import java.util.List;

public interface ProxyRepository<T> extends PagingRepository<T> {
	
	public T executePreparedQueryOne (String preparedQuery, Object[] args);
	
	public List<T> executeByPreparedQueryMore (String preparedQuery, Object[] args);

}
