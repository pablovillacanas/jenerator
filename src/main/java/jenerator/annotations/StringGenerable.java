package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

@Retention(RUNTIME)
@Target(FIELD)
public @interface StringGenerable {

	/**
	 * Minimum length of the string generated. Inclusive, default to 5;
	 */
	int MIN_LENGHT = 5;
	/**
	 * Minimum length of the string generated. Inclusive, default to 10;
	 */
	int MAX_LENGHT = 10;

	/**
	 * @return the minimum lenght of strings beeing generated.
	 */
	int minLenght() default MIN_LENGHT;

	/**
	 * @return the maximum lenght of strings beeing generated.
	 */
	int maxLenght() default MAX_LENGHT;

	/**
	 * Format of string generated. Default
	 * {@link jenerator.engine.generators.StringGenerator.StringSimpleFormat#ONLY_LETTERS
	 * StringSimpleFormat.ONLY_LETTERS}
	 * 
	 * @return the format of string that has to be generated.
	 * @see jenerator.engine.generators.StringGenerator.StringSimpleFormat
	 */
	StringSimpleFormat style() default StringSimpleFormat.ONLY_LETTERS;

	GenerationConstraints constraints() default @GenerationConstraints();

}
