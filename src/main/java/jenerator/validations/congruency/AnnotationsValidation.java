package jenerator.validations.congruency;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.NotGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.POJOUtils;

public class AnnotationsValidation {

	public static <T extends Object> void validate(Class<T> class1) throws AnnotationMismatchFieldException {
		List<Field> fields = POJOUtils.getDeclaredFields(class1);
		for (Iterator<Field> iterator = fields.iterator(); iterator.hasNext();) {
			Field field = iterator.next();
			if (field.getAnnotation(NotGenerable.class) == null) {
				if (validateConcordancy(field))
					System.out.println();
				else
					throw new AnnotationMismatchFieldException();
			}
		}
	}

	private static boolean validateConcordancy(Field field) {
		List<Class<?>> annotationsOfField = Arrays.asList(field.getAnnotations()).stream()
				.filter(ann -> ann.annotationType().getName().startsWith("jenerator.annotations."))
				.map(ann -> ann.annotationType()).collect(Collectors.toList());
		Set<Class<?>> concordantAnnotations = AnnotationsValidation.Utils.retrieveConcordantAnnotationsTo(field);
		for (Class<?> annotation : annotationsOfField) {
			if (!concordantAnnotations.contains(annotation))
				return false;
		}
		return true;
	}

	static public class Utils {

		public static Set<Class<?>> retrieveConcordantAnnotationsTo(Field field) {
			Class<?> class1 = field.getType();
			HashSet<Class<?>> annotations = new HashSet<Class<?>>();
			switch (class1.getCanonicalName()) {
			case "java.lang.Long":

			case "java.lang.Integer":

			case "java.lang.Byte":

			case "java.lang.Short":
				annotations.add(NaturalNumberGenerable.class);
				break;
			case "java.lang.Double":

			case "java.lang.Float":
				annotations.add(DecimalNumberGenerable.class);

				break;
			case "java.lang.String":
				annotations.add(StringGenerable.class);

			default:
				break;
			}
			return annotations;
		}
	}
}
