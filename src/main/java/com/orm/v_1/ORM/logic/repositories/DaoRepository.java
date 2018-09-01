package com.orm.v_1.ORM.logic.repositories;

import java.util.List;

import com.orm.v_1.ORM.query.Query;

public interface DaoRepository<T> {
	
	public List<T> findAll ();
	
	public T findOne (Object id);
	
	public Boolean delete (Object id);
	
	public T save (T object);
	
	public Boolean saveMore (List<T> objects);

	public Boolean updateOne (T object);
	
	public Boolean updateMore (List<T> objects);
	
	public List<T> findByNativeQuery (String query);
	
	public Boolean executeNativeQuery (String query);
	
	public List<T> findBy (Query query);
	
}
