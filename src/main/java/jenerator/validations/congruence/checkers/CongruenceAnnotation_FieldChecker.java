package jenerator.validations.congruence.checkers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.Generable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.NoGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;

/**
 * <p>
 * This class validates at runtime the congruency between the attribute type and
 * the annotation attached.
 * </p>
 * 
 * @author pablo
 *
 */
public class CongruenceAnnotation_FieldChecker implements CongruenceChecker<Field> {

	/**
	 * This method checks that a list of annotated fields are well defined and do
	 * not exist conflicts between annotations type and field type.
	 * 
	 * @param <T>
	 * @param fields
	 * @throws Annotation_FieldCongruenceException if the fields type does not match
	 *                                          with the annotation type.
	 */
	@Override
	public void ckeck(Field field) throws Annotation_FieldCongruenceException {
		if (field.getAnnotation(NoGenerable.class) == null) {
			if (!validateConcordance(field))
				throw new Annotation_FieldCongruenceException();
		}
	}
	
	private static boolean validateConcordance(Field field) {
		List<Class<?>> annotationsOfField = Arrays.asList(field.getAnnotations()).stream()
				.filter(ann -> ann.annotationType().getName().startsWith(Generable.class.getPackage().getName()))
				.map(ann -> ann.annotationType()).collect(Collectors.toList());
		Set<Class<?>> concordantAnnotations = Utils.retrieveConcordantAnnotationsTo(field);
		for (Class<?> annotation : annotationsOfField) {
			if (!concordantAnnotations.contains(annotation))
				return false;
		}
		return true;
	}

	/**
	 * Utils class with the only purpose of provide a set of possibles annotated
	 * types to each field type.
	 * 
	 * @author pablo
	 *
	 */
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
