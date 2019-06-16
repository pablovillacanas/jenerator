package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface NaturalNumberGenerable {

	/**
	 * <p>
	 * Maximum default value in annotation if there are not declared. Default is
	 * Lon.MAX_VALUE.
	 * <p>
	 */
	long MAX_DEFAULT = Long.MAX_VALUE;

	/**
	 * <p>
	 * Minimum default value in annotation if there are not declared. Default is
	 * Lon.MIN_VALUE.
	 * <p>
	 */
	long MIN_DEFAULT = Long.MIN_VALUE;

	/**
	 * <p>
	 * Maximum value included in the generation of values.
	 * </p>
	 * 
	 * @return the maximum value generated for the instances being generated.
	 */
	long maxValue() default MAX_DEFAULT;

	/**
	 * <p>
	 * Maximum value included in the generation of values.
	 * </p>
	 * 
	 * @return the minimum value generated for the instances being generated.
	 * 
	 */
	long minValue() default MIN_DEFAULT;

	GenerationConstraints constraints() default @GenerationConstraints();
}
