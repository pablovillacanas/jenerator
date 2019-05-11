package jenerator.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface GenerationConstraints {

	String DEFAULTSOURCE = "";
	
	String source() default DEFAULTSOURCE;

	boolean unique() default false;

	double nullable() default 0;

}
