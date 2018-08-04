package com.orm.v_1.ORM.tests.test1;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.Constants;
import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.logic.DatabaseDaoFactory;
import com.orm.v_1.ORM.logic.ORM;
import com.orm.v_1.ORM.logic.impl.OrmServiceImpl;
import com.orm.v_1.ORM.query.Query;
import com.orm.v_1.ORM.query.criterion.Criterion;
import com.orm.v_1.ORM.query.criterion.CriterionModel;
import com.orm.v_1.ORM.query.criterion.UnionCriteria;
import com.orm.v_1.ORM.query.criterion.enums.ComparationOperator;
import com.orm.v_1.ORM.query.criterion.enums.QueryOperator;
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
			
//			DatabaseDao<Proffesor> proffesorDao = daoFactory.buildDao(Proffesor.class);
			
//			Proffesor p1 = new Proffesor();
//			p1.setName("Giga");
//			p1.setSurname("Gigic");
//			p1.setDateOfBirth(new Date());
//			p1.setLicence("1AK35LP0");
//			
//			System.out.println("Before: "+p1.toString());
//			proffesorDao.save(p1);
//			System.out.println("After: "+p1.toString());
			
//			List<Proffesor> proffesors = proffesorDao.findAll();
//			System.out.println(proffesors);
			
			DatabaseDao<Subject> subjectDao = daoFactory.buildDao(Subject.class);
			
			//List<Subject> subjects = subjectDao.findByNativeQuery("select * from subject where name like \"Ma%\" or value = 6;");
			
			Subject sub = new Subject();
			sub.setName("Informatika");
			sub.setDescritpion("Od skoro tek obavezan predmet.");
			sub.setValue(8);
			sub.setCodeIdef("1PL045");
			
			subjectDao.save(sub);
			
			List<Subject> subjects = subjectDao.findAll();
			System.out.println(subjects);
			
//			Subject s1 = new Subject();
//			s1.setCodeIdef("OU190");
//			s1.setDescritpion("Koristan predmet.");
//			s1.setName("Matematika");
//			s1.setValue(8);
//			
//			System.out.println("Before: "+s1.toString());
//			subjectDao.save(s1);
//			System.out.println("After: "+s1.toString());
			
//			List<Subject> subjects = subjectDao.findAll();
//			System.out.println(subjects);
//			
//			Criterion criterion = new Criterion("value", 8, ComparationOperator.Equality);
//			Query query = new Query(criterion);
//			List<Subject> subjectsByQuery = subjectDao.findBy(query);
//			System.out.println(subjectsByQuery);
//			
//			Criterion c1 = new Criterion("value", 6, ComparationOperator.Equality);
//			Criterion c2 = new Criterion("name", "Ma", ComparationOperator.Like);
//			List<CriterionModel> criterions = new ArrayList<>();
//			criterions.add(c1);
//			criterions.add(c2);
//			UnionCriteria unionCriteria = new UnionCriteria(criterions, QueryOperator.OR);
//			Query query2 = new Query(unionCriteria);
//			List<Subject> subjectsByOr = subjectDao.findBy(query2);
//			System.out.println(subjectsByOr);			
//			DatabaseDao<SClass> classDao = daoFactory.buildDao(SClass.class);
			
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
			
//			List<SClass> classes = classDao.findAll();
//			System.out.println(classes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}