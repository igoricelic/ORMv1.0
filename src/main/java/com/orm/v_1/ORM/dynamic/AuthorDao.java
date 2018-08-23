package com.orm.v_1.ORM.dynamic;

import java.util.List;

import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.tests.test1.entitiesRel.Author;

public interface AuthorDao extends DatabaseDao<Author> {
	
	public List<Author> findByName (String name);
	
	public Author findBySurname (String surname);

}
