package com.orm.v_1.ORM.logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.orm.v_1.ORM.query.Query;

public interface DatabaseDao<T> {
	
	public List<T> findAll () throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException;
	
	public T findOne (Object id) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException;
	
	public Boolean delete (Object id) throws SQLException;
	
	public T save (T object) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException;
	
	public Boolean saveMore (List<T> objects) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException;

	public Boolean updateOne (T object) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException;
	
	public Boolean updateMore (List<T> objects) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException;
	
	public List<T> findByNativeQuery (String query) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, SQLException;
	
	public Boolean executeNativeQuery (String query) throws SQLException;
	
	public List<T> findBy (Query query) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, ParseException;
	
}
