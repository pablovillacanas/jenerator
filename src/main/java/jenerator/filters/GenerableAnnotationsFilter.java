package jenerator.filters;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

/**
 * <p>
 * This class filters the jenerator annotations from a field.
 * </p>
 * 
 * @author pablo
 *
 */
public class GenerableAnnotationsFilter implements Predicate<Annotation> {

	@Override
	public boolean test(Annotation input) {
		if (input.annotationType().getCanonicalName().startsWith("jenerator"))
			return true;
		else
			return false;
	}
}
