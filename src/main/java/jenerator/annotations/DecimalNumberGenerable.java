package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DecimalNumberGenerable {

	public final int DEFAULT_PRECISION = 2;
	/**
	 * <p>
	 * Maximum value included in the generation of values.
	 * </p>
	 */
	double maxValue() default Double.MAX_VALUE;

	/**
	 * <p>
	 * Minimum value included in the generation of values.
	 * </p>
	 */
	double minValue() default Double.MIN_VALUE;
	
	/**
	 * <p>
	 * NUmber of floating numbers in the generation of values.
	 * </p>
	 */
	short precision() default DEFAULT_PRECISION;

	GenerationConstraints constraints() default @GenerationConstraints();
}
