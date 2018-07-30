package com.orm.v_1.ORM.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @Retention(RetentionPolicy.RUNTIME) @interface Table {
	String name();
}
