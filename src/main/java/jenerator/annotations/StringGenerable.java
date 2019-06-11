package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

@Retention(RUNTIME)
@Target(FIELD)
public @interface StringGenerable {

	public final int DEFAULT_MIN_VALUE = 5;
	public final int DEFAULT_MAX_VALUE = 10;

	/**
	 * Minimum length of the string generated. Inclusive.
	 * 
	 * @return
	 */
	int minLenght() default DEFAULT_MIN_VALUE;

	/**
	 * Maximum length of the string generated. Inclusive.
	 * 
	 * @return
	 */
	int maxLenght() default DEFAULT_MAX_VALUE;

	/**
	 * Format of string generated. Default {@link StringSimpleFormat.ONLY_LETTERS ONLY_LETTERS}
	 * 
	 * @return the format of string that has to be generated.
	 * @see StringSimpleFormat
	 */
	StringSimpleFormat style() default StringSimpleFormat.ONLY_LETTERS;

	GenerationConstraints constraints() default @GenerationConstraints();

}
