package jenerator.configuration.filters;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

/**
 * <p>
 * This class provides the jenerator annotation from a Generable field.
 * </p>
 * 
 * @author pablo
 *
 */
public class GenerableAnnotationsFilter implements Predicate<Annotation> {

	public enum AnnotationFilterType {

		/**
		 * <p>
		 * Gets only proper jenerator annotations
		 * <p>
		 */
		STANDARD,

		/**
		 * <p>
		 * Filters hibernate annotations with their constrints to generate values.
		 * </p>
		 */
		HIBERNATE;
	}

	AnnotationFilterType annotationFilterType;

	@Override
	public boolean test(Annotation input) {
		if (annotationFilterType == AnnotationFilterType.STANDARD) {
			if (input.annotationType().getCanonicalName().startsWith("jenerator"))
				return true;
		} else {
			// TODO immplementation for hibernate
		}
		return false;
	}

	public AnnotationFilterType getAnnotationFilterType() {
		return annotationFilterType;
	}

	public void setAnnotationFilterType(AnnotationFilterType annotationFilterType) {
		this.annotationFilterType = annotationFilterType;
	}

}
