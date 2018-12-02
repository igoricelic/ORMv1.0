package com.orm.v_1.ORM.logic.repositories;

import java.util.List;

public interface Repository<T> {

	public List<T> findByNativeQuery(String query);

	public Boolean executeNativeQuery(String query);
	
	public Integer countAll();

}
