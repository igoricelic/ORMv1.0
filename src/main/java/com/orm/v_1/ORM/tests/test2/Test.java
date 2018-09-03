package com.orm.v_1.ORM.tests.test2;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class Test {
	
	public List<Administrator> test() {
        return null;
    }

    public static void main(String[] args) throws Exception {

        for (Method method : Test.class.getMethods()) {
            Class returnClass = method.getReturnType();
            if (Collection.class.isAssignableFrom(returnClass)) {
                Type returnType = method.getGenericReturnType();
                if (returnType instanceof ParameterizedType) {
                    ParameterizedType paramType = (ParameterizedType) returnType;
                    Type[] argTypes = paramType.getActualTypeArguments();
                    if (argTypes.length > 0) {
                        System.out.println("Generic type is " + argTypes[0]);
                    }
                }
            }
        }

    }

}
