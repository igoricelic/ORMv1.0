package com.orm.v_1.ORM.logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.orm.v_1.ORM.query.Query;

public interface DatabaseDao {
	
	public List<?> findAll (Class<?> entity) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException;
	
	public Object findOne (Class<?> entity, Object id) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException;
	
	public Boolean delete (Class<?> entity, Object id) throws SQLException ;
	
	public Object save (Object object) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException;
	
	public Boolean saveMore (List<OrmEntity> objects) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException;

	public Boolean updateOne (OrmEntity object) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException;
	
	public Boolean updateMore (List<OrmEntity> objects) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException;
	
	public void executeQuery (String query) throws SQLException;
	
	public List<?> executeQuery (Class<?> entity, String query);
	
	public List<?> findBy (Class<?> entity, Query query) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException;
	
}
