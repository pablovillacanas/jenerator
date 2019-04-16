package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface NaturalNumberGenerable{

	long maxValue() default Long.MAX_VALUE;
	long minValue() default Long.MIN_VALUE;
	
}
