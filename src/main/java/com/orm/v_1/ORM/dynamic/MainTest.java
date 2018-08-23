package com.orm.v_1.ORM.dynamic;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.Constants;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.logic.ORM;
import com.orm.v_1.ORM.logic.impl.OrmServiceImpl;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;
import com.orm.v_1.ORM.tests.test1.entities.SClass;
import com.orm.v_1.ORM.tests.test1.entities.Subject;

public class MainTest {
	
	public static void main(String[] args) {
		try {
//			Class<?> cl = ProffesorDao.class.getSuperclass();
//			System.out.println(cl);
//			
//			DatabaseDao<Proffesor> proffesor = null;
//			System.out.println(proffesor.getClass());
			
			ORM orm = new OrmServiceImpl(Constants.HOST, Constants.PORT, Constants.USERNAME, Constants.PASSWORD);
			List<Class<?>> entities = new ArrayList<Class<?>>();
			entities.add(Subject.class);
			entities.add(SClass.class);
			entities.add(Proffesor.class);
			DatabaseDaoFactory daoFactory = orm.generateMapping(Constants.DB, entities, false);
			
			//DatabaseDao<Proffesor> proffesorDao = daoFactory.buildDao(Proffesor.class);

			ProffesorDao proffesorDao = (ProffesorDao) daoFactory.generateProxy(ProffesorDao.class, Proffesor.class);
			
			List<Proffesor> proffesors1 = proffesorDao.selectByName("Pera");
			
			System.out.println(proffesors1.size());
			
			List<Proffesor> proffesors2 = proffesorDao.selectBySurname("Peric");
			
			System.out.println(proffesors2.size());
			
			// Castovanje iz liste u objekat problem
			Proffesor proffesor3 = proffesorDao.selectByLicence("12345");
			
			System.out.println(proffesor3.toString());
			
//			proffesorDao.findBySurname("Peric");
//			
//			proffesorDao.save(null);
//			
//			proffesorDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
