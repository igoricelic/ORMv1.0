package com.orm.v_1.ORM.logic.repositories;

import java.util.List;

import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageRequest;
import com.orm.v_1.ORM.datastructure.SortRequest;

public interface PagingAndSortingRepository<T> extends CrudRepository<T> {
	
	public List<T> findAll (SortRequest sortRequest);
	
	public Page<T> findAll (PageRequest pageRequest);
	
	public Page<T> findAll (PageRequest pageRequest, SortRequest sortRequest);

}
