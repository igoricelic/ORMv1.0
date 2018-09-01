package com.orm.v_1.ORM.tests.test2;

import java.util.Date;
import java.util.List;

import com.orm.v_1.ORM.logic.repositories.DaoRepository;

public interface AdministratorDao extends DaoRepository<Administrator> {
	
	public List<Administrator> findByNameEq (String name);
	
	public List<Administrator> findByNameEqAndSurnameEq (String name, String surname);
	
	public Administrator findByEmailEqAndPasswordEq (String email, String password);
	
	public List<Administrator> findByFavoriteNumberLt (Integer favoriteNumber);
	
	public List<Administrator> findByDateOfRegistrationBefore (Date time);
	
	public List<Administrator> findByDateOfRegistrationAfter (Date time);
	
	public List<Administrator> findByNameStartWith (String prefix);
	
	public List<Administrator> findByNameEndWith (String sufix);
	
	public List<Administrator> findByNameContains (String content);
	
	public List<Administrator> findByDateOfRegistrationBetween (Date time1, Date time2);

}
