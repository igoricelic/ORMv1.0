package com.orm.v_1.ORM.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.orm.v_1.ORM.logic.DatabaseDao;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.Table;
import com.orm.v_1.ORM.query.generator.QueryGenerator;
import com.orm.v_1.ORM.query.generator.QueryGeneratorImpl;

public class DaoProxy<T> implements InvocationHandler {
	
	private DatabaseDao<T> dao;
	
	private Table table;
	
	private QueryGenerator queryGenerator;
	
	public static Object newInstance(Class[] interfaces, Table table, DatabaseDao dao) {
		return Proxy.newProxyInstance(DatabaseDao.class.getClassLoader(), interfaces, new DaoProxy(table, dao));
	}
	
	public DaoProxy(Table table, DatabaseDao<T> dao) {
		this.table = table;
		this.dao = dao;
		this.queryGenerator = new QueryGeneratorImpl();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String query = queryGenerator.generateQuery(method.getName(), table, args);
		return dao.findByNativeQuery(query);
	}

}
