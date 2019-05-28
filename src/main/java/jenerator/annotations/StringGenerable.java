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
	 * <p>
	 * Minimum length included in the generation of values
	 * </p>
	 */
	int minLenght() default DEFAULT_MIN_VALUE;

	/**
	 * <p>
	 * Maximum length included in the generation of values
	 * </p>
	 */
	int maxLenght() default DEFAULT_MAX_VALUE;

	/**
	 * <p>
	 * Sets the collection of characters that will compound the generated strings
	 * </p>
	 * @see StringSimpleFormat
	 */
	StringSimpleFormat style() default StringSimpleFormat.ONLY_LETTERS;

	GenerationConstraints constraints() default @GenerationConstraints();

}
