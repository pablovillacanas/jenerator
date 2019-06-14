package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface NaturalNumberGenerable {

	long MAX_DEFAULT = Long.MAX_VALUE;
	long MIN_DEFAULT = Long.MIN_VALUE;

	/**
	 * <p>
	 * Maximum value included in the generation of values. Default is
	 * Long.MAX_VALUE.
	 * </p>
	 */
	long maxValue() default MAX_DEFAULT;

	/**
	 * <p>
	 * Maximum value included in the generation of values. Default is
	 * Long.MAX_VALUE.
	 * </p>
	 */
	long minValue() default MIN_DEFAULT;

	GenerationConstraints constraints() default @GenerationConstraints();
}
