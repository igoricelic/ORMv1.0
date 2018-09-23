package com.orm.v_1.ORM.tests.test1;

import java.util.List;

import com.orm.v_1.ORM.logic.repositories.DaoRepository;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;

public interface ProffesorDao extends DaoRepository<Proffesor> {
	
	public List<Proffesor> findByNameEq (String name);
	
	public Proffesor findByLicenceEq (String licence);

}
