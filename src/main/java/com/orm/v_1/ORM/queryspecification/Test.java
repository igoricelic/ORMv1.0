package com.orm.v_1.ORM.queryspecification;

import java.util.Collections;

import com.orm.v_1.ORM.queryspecification.builder.SpecificationBuilder;
import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.enums.Operator;

public class Test {
	
	public static void main(String[] args) {
		try {
			Specification specification = 
					new SpecificationBuilder()
					.addCondition("First Column", 1, Comparator.GreaterThan)
					.addCondition(Operator.AND, "Secound Column", 2, Comparator.Equality)
					.addCondition(Operator.OR, "Thirt Column", 3, Comparator.LessThanEquality)
					.limit(10)
					.orderBy("First Column")
					.build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
