package jenerator.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface GenerationConstraints {

	String NONSOURCE = "";

	/**
	 * <p>
	 * Specifies the source from where values will be get. It must be under resource
	 * folder and you have to put the extension of the file.
	 * </p>
	 * 
	 * @return the name of the source file
	 */
	String source() default NONSOURCE;

	/**
	 * <p>
	 * Specifies if the value on this field must be unique in all the instances
	 * generated.
	 * </p>
	 * 
	 * @return boolean indicating if this value must be unique between all the
	 *         instances
	 */
	boolean unique() default false;

	/**
	 * <p>
	 * Specifies the relation of null values in the generation of instances. It
	 * could be useful to improve the generation of instances and also when a
	 * CoverageException arises because it will lower the unique values needed.
	 * </p>
	 * 
	 * @return the relation of null values in the generation of instances.
	 */
	double nullable() default 0;

}
