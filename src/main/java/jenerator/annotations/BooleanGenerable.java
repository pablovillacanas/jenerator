package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Annotation for Boolean values that allows to specify a relation between true
 * and false ones at generation time
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface BooleanGenerable {

	/**
	 * <p>
	 * Relation between true and false values. The values specified here determines
	 * how many true values are going to be generated regarding false ones. Taking
	 * an example, if this the value is 0.6 there will be 6 true values for each 4
	 * false. Also you can no determine this value setting it to one less than 0. In
	 * this case, true and false will be randomly generated.
	 * </p>
	 * 
	 * @return the relation between true and false values
	 */
	double relationTrueFalse() default 0.5d;

	/**
	 * <p>
	 * Relation between true and false values regarding null ones. The nullables
	 * values takes preference which means that if it is put as value 0.3, then, 3
	 * out of 10 values will be null and 7 out of 10 will be calculated by the
	 * relation defined in relationTrueFalse.
	 * </p>
	 * 
	 * @return the relation of nullable values
	 */
	double nullable() default 0.0d;
}
