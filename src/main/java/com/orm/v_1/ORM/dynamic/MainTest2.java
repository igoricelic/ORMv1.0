package com.orm.v_1.ORM.dynamic;

import java.lang.reflect.Field;

import com.orm.v_1.ORM.interpreter.Interpreter;
import com.orm.v_1.ORM.interpreter.impl.InterpreterImpl;

public class MainTest2 {
	
	public static void main(String[] args) {
		Interpreter interpreter = new InterpreterImpl(null);
		
		try {
			String clazzName = interpreter.getClass().getTypeName();
			Class<?> clazz = Class.forName(clazzName);
			System.out.println(clazz.getName());
			for(Field field: clazz.getDeclaredFields()) {
				System.out.println(field.toString());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
