package jenerator.filters;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

/**
 * <p>
 * This class provides the jenerator annotation from a Generable field
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
