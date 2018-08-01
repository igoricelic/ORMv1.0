package com.orm.v_1.ORM.tests.test1;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.Constants;
import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.logic.ORM;
import com.orm.v_1.ORM.logic.impl.OrmServiceImpl;
import com.orm.v_1.ORM.tests.test1.entities.Proffesor;
import com.orm.v_1.ORM.tests.test1.entities.SClass;
import com.orm.v_1.ORM.tests.test1.entities.Subject;

public class MainTest1 {
	
	public static void main(String[] args) {
		try {
			ORM orm = new OrmServiceImpl(Constants.HOST, Constants.PORT, Constants.USERNAME, Constants.PASSWORD);
			List<Class<?>> entities = new ArrayList<Class<?>>();
			entities.add(Subject.class);
			entities.add(SClass.class);
			entities.add(Proffesor.class);
			DatabaseDaoFactory daoFactory = orm.generateMapping(Constants.DB, entities, false);
			
			DatabaseDao<Proffesor> proffesorDao = daoFactory.buildDao(Proffesor.class);
			
//			Proffesor p1 = new Proffesor();
//			p1.setName("Giga");
//			p1.setSurname("Gigic");
//			p1.setDateOfBirth(new Date());
//			p1.setLicence("1AK35LP0");
//			
//			System.out.println("Before: "+p1.toString());
//			proffesorDao.save(p1);
//			System.out.println("After: "+p1.toString());
			
			List<Proffesor> proffesors = proffesorDao.findAll();
			System.out.println(proffesors);
			
			DatabaseDao<Subject> subjectDao = daoFactory.buildDao(Subject.class);
			
//			Subject s1 = new Subject();
//			s1.setCodeIdef("OU190");
//			s1.setDescritpion("Koristan predmet.");
//			s1.setName("Matematika");
//			s1.setValue(8);
//			
//			System.out.println("Before: "+s1.toString());
//			subjectDao.save(s1);
//			System.out.println("After: "+s1.toString());
			
			List<Subject> subjects = subjectDao.findAll();
			System.out.println(subjects);
			
			DatabaseDao<SClass> classDao = daoFactory.buildDao(SClass.class);
			
//			SClass c1 = new SClass();
//			c1.setClassRoom("U5");
//			c1.setDateTime(new Date());
//			c1.setDescription("Cas je odrzan sa 15 minuta zakasnjenja.");
//			c1.setProffesorId(1);
//			c1.setSubjectId(1);
//			
//			System.out.println("Before: "+c1.toString());
//			classDao.save(c1);
//			System.out.println("After: "+c1.toString());
			
			List<SClass> classes = classDao.findAll();
			System.out.println(classes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
