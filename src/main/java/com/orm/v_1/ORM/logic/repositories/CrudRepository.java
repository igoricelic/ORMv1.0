package com.orm.v_1.ORM.logic.repositories;

import java.util.List;

public interface CrudRepository<T> extends Repository<T> {
	
	public T findOne ();
	
	public List<T> findAll ();
	
	public Boolean deleteOne (T object);
	
	public Boolean deleteMode (List<T> objects);
	
	public Boolean deleteAll ();
	
	public T saveOne (T object);
	
	public Boolean saveMore (List<T> objects);
	
	public T updateOne (T object);
	
	public Boolean updateMore (List<T> objects);
	
}
