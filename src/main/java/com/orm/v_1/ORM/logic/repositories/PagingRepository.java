package com.orm.v_1.ORM.logic.repositories;

import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageRequest;

public interface PagingRepository<T> extends CrudRepository<T> {
	
	public Page<T> findAll (PageRequest pageRequest);

}
