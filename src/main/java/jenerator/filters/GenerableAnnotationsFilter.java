package jenerator.filters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import jenerator.filters.exceptions.NotAnnotationEncountered;

/**
 * <p>
 * This class provides the jenerator annotation from a Generable field
 * </p>
 * 
 * @author pablo
 *
 */
public class GenerableAnnotationsFilter {

	/**
	 * <p>
	 * This method returns the first GenerableAnnotation encountered over a field
	 * </p>
	 * 
	 * @param generableField
	 * @return
	 * @throws NotAnnotationEncountered if the field has not GenerableAnnotation
	 */
	public static Annotation retrieveGenerableAnnotation(Field generableField) throws NotAnnotationEncountered {
		Annotation annotation = Arrays.asList(generableField.getAnnotations()).stream()
				.filter(ann -> ann.annotationType().getCanonicalName().startsWith("jenerator"))
				.collect(Collectors.toList()).stream().findFirst().get();
		if (annotation == null) {
			throw new NotAnnotationEncountered(
					"No generable annotation encountered. This may be occured when you lazy filter the fields due to automatic instance generator posting Generable class annotation. Please change Configuration settings.");
		}
		return annotation;
	}
}
