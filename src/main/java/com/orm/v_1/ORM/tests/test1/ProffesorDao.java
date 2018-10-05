package com.orm.v_1.ORM.tests.test1;

import java.util.List;

import com.orm.v_1.ORM.logic.repositories.OrmRepository;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;

public interface ProffesorDao extends OrmRepository<Proffesor> {
	
	public List<Proffesor> findByNameEq (String name);
	
	public Proffesor findByLicenceEq (String licence);

}
