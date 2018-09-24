package com.orm.v_1.ORM.logic.repositories;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageRequest;
import com.orm.v_1.ORM.queryspecification.Specification;

public interface OrmRepository<T> extends PagingRepository<T> {
	
	public Optional<T> findById (Object id);
	
	public Optional<List<T>> findBySpecification (Specification specification);
	
	public Optional<Page<T>> findBySpecification (Specification specification, PageRequest pageRequest);

}
