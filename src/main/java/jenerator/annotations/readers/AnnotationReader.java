package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.readers.exceptions.AnnotationConstraintsException;

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
	public Constraints readValues(Annotation annotation) throws AnnotationConstraintsException {
		if (annotation instanceof NaturalNumberGenerable)
			return new NaturalNumberConstraintsReader().readValues((NaturalNumberGenerable) annotation);
		if (annotation instanceof StringGenerable)
			return new StringConstraintsReader().readValues((StringGenerable) annotation);
		return null;
	}
}
