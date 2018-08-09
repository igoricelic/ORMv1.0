package com.orm.v_1.ORM.annotations.join;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @Retention(RetentionPolicy.RUNTIME) @interface ForeignKey {
	String name() default "";
}

