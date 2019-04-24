package jenerator.annotations.reader;

import java.lang.annotation.Annotation;

import jenerator.annotations.NaturalNumberGenerable;

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
	@Override
	public Constraints readValues(Annotation annotation) {
		if (annotation instanceof NaturalNumberGenerable)
			return new NaturalNumberGeneratorReader().readValues((NaturalNumberGenerable) annotation);
		return null;
	}
}
