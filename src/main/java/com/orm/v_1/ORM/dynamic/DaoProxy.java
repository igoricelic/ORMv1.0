package com.orm.v_1.ORM.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import com.orm.v_1.ORM.interpreter.Interpreter;
import com.orm.v_1.ORM.interpreter.impl.InterpreterImpl;
import com.orm.v_1.ORM.logic.repositories.DaoRepository;
import com.orm.v_1.ORM.logic.repositories.ProxyRepository;
import com.orm.v_1.ORM.model.Table;

public class DaoProxy<T> implements InvocationHandler {
	
	public static Object newInstance(Class<?>[] interfaces, Table table, ProxyRepository<?> dao) {
		return Proxy.newProxyInstance(DaoRepository.class.getClassLoader(), interfaces, new DaoProxy(table, dao));
	}
	
	private ProxyRepository<T> dao;
	
	private Interpreter interpreter;
	
	public DaoProxy(Table table, ProxyRepository<T> dao) {
		this.dao = dao;
		this.interpreter = new InterpreterImpl(table);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			try {
				return dao.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(dao, args);
			} catch (NoSuchMethodException e) {
				boolean isList = false;
				Type type = method.getGenericReturnType();
		        if (type instanceof ParameterizedType) {
		        	isList = true;
		        	// Provera da li je validan tip liste??? 
		            //ParameterizedType pt = (ParameterizedType) type;
		            //for (Type t : pt.getActualTypeArguments()) {
		            //    System.out.println("    " + t);
		            //}
		        }
		        String query = this.interpreter.buildStatement(method);
		        if(!isList) return this.dao.executePreparedQueryOne(query, args);
				return this.dao.executeByPreparedQueryMore(query, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
