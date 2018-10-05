package com.orm.v_1.ORM.logic.repositories;

import java.util.List;

public interface CrudRepository<T> extends Repository<T> {
	
	public List<T> findAll ();
	
	public T findOne (Object id);
	
	public Boolean delete (Object id);
	
	public Boolean deleteAll ();
	
	public T save (T object);
	
	public Boolean saveMore (List<T> objects);
	
	public Boolean saveMoreAnotherWay (List<T> objects);
	
	public T updateOne (T object);
	
	public Boolean updateMore (List<T> objects);
	
}
