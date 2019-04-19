package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
@interface GenerationConstraints {

	String source() default "document.jen";

	boolean unique() default false;
	
	double nullable() default 0;

}
