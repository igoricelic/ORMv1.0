package com.orm.v_1.ORM.tests.test1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orm.v_1.ORM.Constants;
import com.orm.v_1.ORM.logic.DatabaseDao;
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
			DatabaseDao dao = orm.generateMapping(Constants.DB, entities, false);
			
//			Proffesor p1 = new Proffesor();
//			p1.setName("Peka");
//			p1.setSurname("Pekic");
//			p1.setDateOfBirth(new Date());
//			p1.setLicence("svdgsfs");
//			
//			System.out.println("Before: "+p1.toString());
//			p1 = (Proffesor) dao.save(p1);
//			System.out.println("After: "+p1.toString());
			
			Subject s1 = new Subject();
			s1.setCodeIdef("PL153");
			s1.setDescritpion("Zanimljiv predmet.");
			s1.setName("Fizika");
			s1.setValue(8);
			
			System.out.println("Before: "+s1.toString());
			dao.save(s1);
			System.out.println("After: "+s1.toString());
			
			SClass c1 = new SClass();
			c1.setClassRoom("U1");
			c1.setDateTime(new Date());
			c1.setDescription("Cas je odrzan u miru i tisini");
			c1.setProffesorId(1);
			c1.setSubjectId(1);
			
			System.out.println("Before: "+c1.toString());
			c1 = (SClass) dao.save(c1);
			System.out.println("After: "+c1.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
