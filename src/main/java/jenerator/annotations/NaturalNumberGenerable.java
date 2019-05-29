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
	 * Maximum value included in the generation of values
	 * </p>
	 */
	long maxValue() default Long.MAX_VALUE;

	/**
	 * <p>
	 * Minimum value included in the generation of values
	 * </p>
	 */
	long minValue() default Long.MIN_VALUE;

	GenerationConstraints constraints() default @GenerationConstraints();
}
