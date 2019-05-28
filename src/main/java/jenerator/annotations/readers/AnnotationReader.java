package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.Constraints;

/**
 * <p>
 * This class provides the constraints of Annotation
 * </p>
 * 
 * @author pablo
 *
 */
public class AnnotationReader implements IAnnotationReader<Annotation> {

	/**
	 * <p>
	 * This method retrieves a Constraint object for an annotation type.
	 * </p>
	 * <p>
	 * You must cast the Constraint Object to the correct Constraint type to have
	 * access to all the parameters of the constraint.
	 * </p>
	 */
	public Constraints readValues(Annotation annotation) {
		if (annotation instanceof NaturalNumberGenerable) {
			return new NaturalNumberConstraintsReader<NaturalNumberGenerable>()
					.readValues((NaturalNumberGenerable) annotation);
		}
		if (annotation instanceof StringGenerable)
			return new StringConstraintsReader<StringGenerable>().readValues((StringGenerable) annotation);
		return null;
	}

}
