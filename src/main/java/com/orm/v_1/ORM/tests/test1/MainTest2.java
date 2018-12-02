package com.orm.v_1.ORM.tests.test1;

import java.util.Date;
import java.util.List;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;

public class MainTest2 {
	
	public static void main(String[] args) {
		try {
			String path = "/Users/igoricelic/Documents/workspace-sts-3.8.4.RELEASE/ORM/src/main/java/com/orm/v_1/ORM/tests/test1/config_file.orm";
			OrmConfig.setConfiguration(path);
			
			ProffesorDao proffesorDao = (ProffesorDao) OrmConfig.buildProxy(ProffesorDao.class, Proffesor.class);
			
			// proffesorDao.deleteAll();
			List<Proffesor> proffesors = proffesorDao.findAll();
			for(Proffesor proffesor: proffesors) {
				String newLicence = proffesor.getLicence() + "-101";
				proffesor.setLicence(newLicence);
				proffesorDao.updateOne(proffesor);
			}
			
			Proffesor p1 = new Proffesor();
			p1.setDateOfBirth(new Date());
			p1.setLicence("A64-301");
			p1.setName("Pera");
			p1.setSurname("Peric");
			proffesorDao.saveOne(p1);
			
			Proffesor p2 = new Proffesor();
			p2.setDateOfBirth(new Date());
			p2.setLicence("B78-117");
			p2.setName("Pera");
			p2.setSurname("Grubic");
			proffesorDao.saveOne(p2);
			
			Proffesor p3 = new Proffesor();
			p3.setDateOfBirth(new Date());
			p3.setLicence("C21-Q16");
			p3.setName("Mika");
			p3.setSurname("Mikic");
			proffesorDao.saveOne(p3);
			
			System.out.println(proffesorDao.findAll());
			
			System.out.println(proffesorDao.findByNameEq("Pera"));
			
			System.out.println(proffesorDao.findByLicenceEq("C21-Q16"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
