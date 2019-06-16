package jenerator.validations.congruence;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.configuration.JeneratorConfiguration;

/**
 * <p>
 * This class validates at runtime the congruence between the attribute type and
 * the annotation attached. Its a filter to collect all those fields that do not
 * match with their attached annotation.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class FieldCongruenceChecker implements Predicate<Field> {

	JeneratorConfiguration engineConfiguration = JeneratorConfiguration.getInstance();

	/**
	 * <p>
	 * This method checks that a list of annotated fields are well defined and do
	 * not exist conflicts between annotations type and field type.
	 * </p>
	 * 
	 * @param field field to be tested for congruence with its annotation.
	 * @return true if the field does does not fit with the annotation attached
	 *         with, false otherwise
	 */

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean test(Field field) {
		Optional<Annotation> annotation = Arrays.asList(field.getAnnotations()).stream()
				.filter(engineConfiguration.getGenerableAnnotationFilter()).findFirst();
		// We have to check this because with lazy filter the field may not have
		// annotation attached
		if (annotation.isPresent()) {
			Set<Class<?>> concordantAnnotations = ConcordantAnnotationRetriever.retrieveConcordantAnnotationsOf(field);
			if (concordantAnnotations.contains(annotation)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * This class it is a helper class that provides a way to check if a field has
	 * the correct Annotation attached.
	 * </p>
	 * 
	 * <table border="1">
	 * <caption>Java types and their correspondent annotations</caption>
	 * <tr>
	 * <th>Java Type</th>
	 * <th>Annotation Type</th>
	 * </tr>
	 * <tr>
	 * <td>{@link java.lang.Long Long}</td>
	 * <td rowspan="4">{@link jenerator.annotations.NaturalNumberGenerable
	 * NaturalNumberGenerable}</td>
	 * </tr>
	 * <tr>
	 * <td>{@link java.lang.Integer Integer}</td>
	 * </tr>
	 * 
	 * <tr>
	 * <td>{@link java.lang.Short Short}</td>
	 * </tr>
	 *
	 * <tr>
	 * <td>{@link java.lang.Byte Byte}</td>
	 * </tr>
	 *
	 * <tr>
	 * <td>{@link java.lang.Double Double}</td>
	 * <td rowspan="2">{@link jenerator.annotations.DecimalNumberGenerable
	 * DecimalNumberGenerable}</td>
	 * </tr>
	 *
	 * <tr>
	 * <td>{@link java.lang.Float Float}</td>
	 * </tr>
	 *
	 * <tr>
	 * <td>{@link java.lang.String String}</td>
	 * <td rowspan="2">{@link jenerator.annotations.StringGenerable
	 * StringGenerable}</td>
	 * </tr>
	 *
	 * <tr>
	 * <td>{@link java.lang.Character Character}</td>
	 * </tr>
	 * 
	 * <tr>
	 * <td>{@link java.lang.Boolean Boolean}</td>
	 * <td>{@link jenerator.annotations.BooleanGenerable BooleanGenerable}</td>
	 * </tr>
	 * 
	 *
	 * <tr>
	 * <td>{@link java.lang.Object Object} with
	 * {@link jenerator.annotations.Generable Generable} annotation</td>
	 * <td>{@link jenerator.annotations.StringGenerable StringGenerable}</td>
	 * </tr>
	 * </table>
	 * 
	 * @author Pablo Villacanas
	 *
	 */
	static public class ConcordantAnnotationRetriever {

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
