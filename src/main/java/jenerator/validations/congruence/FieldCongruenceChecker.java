package jenerator.validations.congruence;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.utils.FieldUtils;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;

/**
 * <p>
 * This class validates at runtime the congruence between the attribute type and
 * the annotation attached. Its a filter to collect all those fields that do not
 * match with their atatched annotation.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class FieldCongruenceChecker implements Predicate<Field> {

	/**
	 * <p>
	 * This method checks that a list of annotated fields are well defined and do
	 * not exist conflicts between annotations type and field type.
	 * </p>
	 * 
	 * @param <T>
	 * @param fields
	 * @return true if the field does does not fit with the annotation attached
	 *         with, false otherwise
	 * @throws Annotation_FieldCongruenceException if the fields type does not match
	 *                                             with the annotation type.
	 */

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean test(Field field) {
		Annotation annotation = FieldUtils.getGenerableAnnotation(field);
		Set<Class<?>> concordantAnnotations = Utils.retrieveConcordantAnnotationsOf(field);
		FieldUtils.getGenerableAnnotation(field);
		if (concordantAnnotations.contains(annotation)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Utils class with the only purpose of provide a set of possibles annotated
	 * types to each field type.
	 * </p>
	 * 
	 * @author Pablo Villacanas
	 *
	 */
	static public class Utils {

		public static Set<Class<?>> retrieveConcordantAnnotationsOf(Field field) {
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
