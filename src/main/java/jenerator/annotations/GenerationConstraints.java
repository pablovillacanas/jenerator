package jenerator.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation provides the common values constraints to all type of fields.
 * </p>
 * 
 * @author pablo
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface GenerationConstraints {

	String NONSOURCE = "";

	// TODO make an annotation to parse Sources by delimiter instead file extension
	/**
	 * <p>
	 * Set the source from where values will be extracted if applicable, like a
	 * text. plain file.
	 * </p>
	 */
	String source() default NONSOURCE;

	/**
	 * <p>
	 * Set the amount of values that can be null in a set of objects generation in
	 * decimal format - 0 to 1. Defaults to 0, that means that there will be no null
	 * values.
	 * </p>
	 */
	boolean unique() default false;

	/**
	 * <p>
	 * Set the amount of values that can be null in a set of objects generation in
	 * decimal format. Defaults to 0, that means that there will be no null values.
	 * If you want all values to be null use.
	 * {@link jenerator.annotations.NoGenerable NoGenerable} instead.
	 * </p>
	 */
	double nullable() default 0;

}
