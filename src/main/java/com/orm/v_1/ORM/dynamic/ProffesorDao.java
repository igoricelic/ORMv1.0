package com.orm.v_1.ORM.dynamic;

import java.util.List;

import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;

public interface ProffesorDao extends DatabaseDao<Proffesor> {
	
	public List<Proffesor> selectByName (String name);
	
	public List<Proffesor> selectBySurname (String surname);
	
	public Proffesor selectByLicence (String licence);

}
