package com.orm.v_1.ORM.tests.test2;

import java.util.Date;
import java.util.List;

import com.orm.v_1.ORM.annotations.NativeQuery;
import com.orm.v_1.ORM.logic.repositories.CrudRepository;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;
import com.orm.v_1.ORM.tests.test1.entities.SClass;

public interface AdministratorDao extends CrudRepository<Administrator> {
	
	public List<Administrator> findByNameEq (String name);
	
	public List<Administrator> findByNameEqLimitFrom (String name, Integer startPosition, Integer size);
	
	public List<Administrator> findByNameEqAndSurnameEq (String name, String surname);
	
	public Administrator findByEmailEqAndPasswordEq (String email, String password);
	
	public List<Administrator> findByFavoriteNumberLt (Integer favoriteNumber);
	
	public List<Administrator> findByDateOfRegistrationBefore (Date time);
	
	public List<Administrator> findByDateOfRegistrationAfter (Date time);
	
	public List<Administrator> findByNameStartWith (String prefix);
	
	public List<Administrator> findByNameEndWith (String sufix);
	
	public List<Administrator> findByNameContains (String content);
	
	public List<Administrator> findByDateOfRegistrationBetween (Date time1, Date time2);
	
	public List<Administrator> findByFavoriteNumberLteOrFavoriteNumberGte (Integer lb, Integer up);
	
	@NativeQuery(value = "select * from admin where name = ?")
	public List<Administrator> fetchByName (String name);
	
	public Proffesor findByEmailEq (String email);
	
	public List<SClass> findBySurnameEq (String surname);

}
