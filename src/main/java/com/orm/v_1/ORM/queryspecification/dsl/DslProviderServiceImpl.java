package com.orm.v_1.ORM.queryspecification.dsl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;
import com.orm.v_1.ORM.interpreter.exceptions.InvalidTokenException;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.enums.Operator;
import com.orm.v_1.ORM.queryspecification.model.Condition;

public class DslProviderServiceImpl implements DslProviderService {
	
	private static final String[] ALL_OPERATION_SET = { ":", "~", ">", "<", ">:", "<:", "!" };

	private Comparator getComparatorForInput (String input) throws InvalidTokenException {
		switch (input) {
			case ":":
				return Comparator.Equality;
			case "~":
				return Comparator.Contains;
			case ">":
				return Comparator.GreaterThan;
			case "<":
				return Comparator.LessThan;
			case ">:":
				return Comparator.GreaterThanEquality;
			case "<:":
				return Comparator.LessThanEquality;
			case "!":
				return Comparator.NotEquals;
			default:
				throw new InvalidTokenException(String.format("Invalid token %s in DSL expression!", input));
		}
	}
	
	@Override
	public Specification provide(String searchSpecificatin) throws InvalidTokenException {
		String operationSetExper = Joiner.on("|").join(ALL_OPERATION_SET);
		Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")([\\w- ]+?),");
		
		Matcher matcher = pattern.matcher(searchSpecificatin + ",");
		
		List<Condition> conditions = new ArrayList<>();
		boolean firstElement = true;
		while(matcher.find()) {
			if (firstElement) {
				conditions.add(new Condition(matcher.group(1), matcher.group(3), getComparatorForInput(matcher.group(2))));
				firstElement = false;
				continue;
			}
			conditions.add(new Condition(Operator.AND, matcher.group(1), matcher.group(3), getComparatorForInput(matcher.group(2))));
		}
		
		try {
			try {
				Constructor<Specification> constructor = Specification.class.getDeclaredConstructor(List.class);
				constructor.setAccessible(true);
				return constructor.newInstance(conditions);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			DslProviderService dslProviderService = new DslProviderServiceImpl();
			Specification spec1 = dslProviderService.provide("name:Igor");
			Specification spec2 = dslProviderService.provide("name~Igor I,surname:Icelic,age>18");
			System.out.println("kraj");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
