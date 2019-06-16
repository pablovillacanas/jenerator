package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.Constraints;

/**
 * <p>
 * This class provides the constraints of Annotation
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class AnnotationParser {

	/**
	 * <p>
	 * This method retrieves a Constraint object for an annotation type.
	 * </p>
	 * <p>
	 * You must cast the Constraint Object to the correct Constraint type to have
	 * access to all the parameters of the constraint.
	 * </p>
	 * 
	 * @param annotation The annotation to be parsed
	 * @return The constraints contained by the annotation
	 */
	public Constraints parse(Annotation annotation) {
		if (annotation instanceof NaturalNumberGenerable)
			return new NaturalNumberConstraintsReader().readValues((NaturalNumberGenerable) annotation);
		if (annotation instanceof StringGenerable)
			return new StringConstraintsReader().readValues((StringGenerable) annotation);
		if (annotation instanceof DecimalNumberGenerable)
			return new DecimalNumberConstraintsReader().readValues((DecimalNumberGenerable) annotation);
		return null;
	}
}
