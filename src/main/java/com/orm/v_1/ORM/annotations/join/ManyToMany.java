package com.orm.v_1.ORM.annotations.join;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @Retention(RetentionPolicy.RUNTIME) @interface ManyToMany {
	String table() default "";
	String column() default "";
	String inverse_column() default "";
	String mapped_by() default "";

}
