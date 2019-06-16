package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DecimalNumberGenerable {

	long MAX_DEFAULT = Long.MAX_VALUE;
	long MIN_DEFAULT = Long.MIN_VALUE;
	int PRECISION = 2;

	/**
	 * <p>
	 * Maximum value included in the generation of values. Default is
	 * Double.MAX_VALUE.
	 * </p>
	 * 
	 * @return the maximum value generated for the instances being generated
	 */
	double maxValue() default MAX_DEFAULT;

	/**
	 * <p>
	 * Minimum value included in the generation of values. Default is
	 * Double.MIN_VALUE.
	 * </p>
	 * 
	 * @return the minimum value generated for the instances being generated
	 */
	double minValue() default MIN_DEFAULT;

	/**
	 * <p>
	 * Number of floating numbers in the generation of values. If it is not set
	 * default is 2.
	 * </p>
	 * 
	 * @return the number of digits in the decimal part to be generated.
	 */
	short precision() default PRECISION;

	GenerationConstraints constraints() default @GenerationConstraints();
}
