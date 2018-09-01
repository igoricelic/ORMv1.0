package com.orm.v_1.ORM.tests.test2;

import java.util.List;

import com.orm.v_1.ORM.logic.repositories.DaoRepository;

public interface AdministratorDao extends DaoRepository<Administrator> {
	
	public List<Administrator> findByNameEq (String name);
	
	public List<Administrator> findByNameEqAndSurnameEq (String name, String surname);
	
	public Administrator findByEmailEqAndPasswordEq (String email, String password);
	
	public List<Administrator> findByFavoriteNumberLt (Integer favoriteNumber);

}
