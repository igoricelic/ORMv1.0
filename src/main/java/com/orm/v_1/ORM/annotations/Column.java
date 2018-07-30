package com.orm.v_1.ORM.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.orm.v_1.ORM.enums.ColumnType;

public @Retention(RetentionPolicy.RUNTIME) @interface Column {
	String name() default "";
	ColumnType type() default ColumnType.AUTO;
	int length() default -1;
}
