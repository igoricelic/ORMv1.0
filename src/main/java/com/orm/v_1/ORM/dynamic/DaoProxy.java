package com.orm.v_1.ORM.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import com.orm.v_1.ORM.exceptions.NotCompatibleTypesException;
import com.orm.v_1.ORM.interpreter.Interpreter;
import com.orm.v_1.ORM.interpreter.impl.InterpreterImpl;
import com.orm.v_1.ORM.logic.repositories.DaoRepository;
import com.orm.v_1.ORM.logic.repositories.ProxyRepository;
import com.orm.v_1.ORM.model.Table;

public class DaoProxy<T> implements InvocationHandler {
	
	public static Object newInstance(Class<?>[] interfaces, Table table, Class<?> entityClass, ProxyRepository<?> dao) {
		return Proxy.newProxyInstance(DaoRepository.class.getClassLoader(), interfaces, new DaoProxy(table, entityClass, dao));
	}
	
	private ProxyRepository<T> dao;
	
	private Interpreter interpreter;
	
	private Class<?> entityClass;
	
	public DaoProxy(Table table, Class<?> entityClass, ProxyRepository<T> dao) {
		this.dao = dao;
		this.interpreter = new InterpreterImpl(table);
		this.entityClass = entityClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable, NotCompatibleTypesException {
		try {
			try {
				return dao.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(dao, args);
			} catch (NoSuchMethodException e) {
				boolean isList = false;
				
				Type type =  method.getGenericReturnType();
		        if (type instanceof ParameterizedType) {
		        	isList = true;
		        	if (type instanceof ParameterizedType) {
	                    ParameterizedType paramType = (ParameterizedType) type;
	                    Type[] argTypes = paramType.getActualTypeArguments();
	                    if (argTypes.length > 0) {
	                       if(!this.entityClass.equals(argTypes[0])) throw new NotCompatibleTypesException("Not compatible types: "+this.entityClass.toGenericString()+" and "+argTypes[0]);
	                    }
	                }
		        } else {
		        	if(!this.entityClass.equals(type)) throw new NotCompatibleTypesException("Not compatible types: "+this.entityClass.toGenericString()+" and "+type);
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
