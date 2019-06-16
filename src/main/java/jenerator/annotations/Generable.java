package jenerator.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jenerator.configuration.filters.GenerableFieldsFilter.FieldFilterType;

/**
 * <p>
 * This annotation is the entry point for all classes that are wanted to being
 * generated. All classes annotated as {@link jenerator.annotations.Generable
 * Generable} by default will generate values for all fields but annotated with
 * {@link jenerator.annotations.NoGenerable NoGenerable}. To change this
 * behavior set to annotation property other value.
 * </p>
 * 
 * @author Pablo Villacanas
 * @see jenerator.configuration.filters.GenerableFieldsFilter.FieldFilterType
 *      FieldFilterType
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Generable {

	/**
	 * @return the mode which determines the field able to be generated.
	 */
	FieldFilterType getFieldFilter() default FieldFilterType.LAZYFILTER;
}
