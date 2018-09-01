package com.orm.v_1.ORM.tests.test2;

import java.util.Arrays;
import java.util.List;

import com.orm.v_1.ORM.Constants;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.logic.ORM;
import com.orm.v_1.ORM.logic.impl.OrmServiceImpl;


public class MainTest {
	
	public static void main(String[] args) {
		try {
			ORM orm = new OrmServiceImpl(Constants.HOST, Constants.PORT, Constants.USERNAME, Constants.PASSWORD);
			List<Class<?>> entities = Arrays.asList(Administrator.class);
			DatabaseDaoFactory daoFactory = orm.generateMapping(Constants.DB, entities, false);
			
			AdministratorDao administratorDao = (AdministratorDao) daoFactory.generateProxy(AdministratorDao.class, Administrator.class);
			
			List<Administrator> lAdministrators = administratorDao.findAll();
			System.out.println(lAdministrators);
			
			List<Administrator> lAdministratorsPera = administratorDao.findByNameEq("Pera");
			System.out.println(lAdministratorsPera);
			
			List<Administrator> res1 = administratorDao.findByNameEqAndSurnameEq("Zika", "Zikic");
			System.out.println(res1);
			
			List<Administrator> res2 = administratorDao.findByFavoriteNumberLt(9);
			System.out.println(res2);
			
			Administrator admin1 = administratorDao.findByEmailEqAndPasswordEq("pera.peric@gmail.com", "pera");
			System.out.println(admin1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	Administrator a1 = new Administrator();
//	a1.setName("Pera");
//	a1.setSurname("Peric");
//	a1.setEmail("pera.peric@gmail.com");
//	a1.setDateOfRegistration(new Date());
//	a1.setFavoriteNumber(10);
//	a1.setPassword("pera");
//	
//	administratorDao.save(a1);
//	
//	Administrator a2 = new Administrator();
//	a2.setName("Mika");
//	a2.setSurname("Mikic");
//	a2.setEmail("mika.mikic@gmail.com");
//	a2.setDateOfRegistration(new Date());
//	a2.setFavoriteNumber(13);
//	a2.setPassword("mika");
//	
//	administratorDao.save(a2);
//	
//	Administrator a3 = new Administrator();
//	a3.setName("Zika");
//	a3.setSurname("Zikic");
//	a3.setEmail("zika.zikic@gmail.com");
//	a3.setDateOfRegistration(new Date());
//	a3.setFavoriteNumber(7);
//	a3.setPassword("zika");
//	
//	administratorDao.save(a3);
//	
//	Administrator a4 = new Administrator();
//	a4.setName("Pera");
//	a4.setSurname("Dugalic");
//	a4.setEmail("pera.dugalic@gmail.com");
//	a4.setDateOfRegistration(new Date());
//	a4.setFavoriteNumber(5);
//	a4.setPassword("dugalic");
//	
//	administratorDao.save(a4);

}