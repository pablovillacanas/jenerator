package jenerator.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.AnnotationParser;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.DecimalNumberGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.StringGenerator;
import jenerator.engine.generators.ValueGenerator;
import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;
import jenerator.utils.ClassUtils;
import jenerator.utils.FieldUtils;

/**
 * <p>
 * This class populates a list of instance.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class GeneratorController {

	private final AnnotationParser annotationParser = new AnnotationParser();
	private List<? extends Object> instances;
	private Class<?> actualClass;
	private Map<Method, ValueGenerator<? extends Object>> method_values;

	public GeneratorController(Class<?> class1, List<? extends Object> instances) {
		this.instances = instances;
		this.actualClass = class1;
		method_values = new HashMap<Method, ValueGenerator<? extends Object>>();

	}

	public void process() throws CoverageExceededException, NoSuitableElementsOnSource, ElementFromSourceException {
		List<Field> generableFields = ClassUtils.getGenerableFields(actualClass);
		int quantity = instances.size();
		for (Field field : generableFields) {
			Method getter = FieldUtils.getterOf(field);
			ValueGenerator<? extends Object> generatedValues = createValueGenerator(quantity, field);
			generatedValues.generate();
			method_values.put(getter, generatedValues);
		}
		for (Object t : instances) {
			method_values.forEach((m, vg) -> {
				try {
					// TODO simplificar buscando el tipo del parámetro del metodo
					Class<?> type = FieldUtils.fieldOf(m).getType();
					m.invoke(t, type.cast(vg.getValue()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		}
	}

	@SuppressWarnings({ "unchecked" })
	private <E extends Object> ValueGenerator<? extends E> createValueGenerator(long quantity, Field field)
			throws CoverageExceededException, ElementFromSourceException {
		ValueGenerator<? extends Object> valueGenerator = null;
		Class<?> fieldType = field.getType();
		Annotation generableAnnotation = FieldUtils.getGenerableAnnotation(field);
		Constraints constraints = annotationParser.parse(generableAnnotation);
		if (Number.class.isAssignableFrom(fieldType)) {
			if (Long.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)
					|| Short.class.isAssignableFrom(fieldType) || Byte.class.isAssignableFrom(fieldType)) {
				valueGenerator = new NaturalNumberGenerator<Number>(fieldType, quantity,
						(NaturalNumberConstraints) constraints);
			} else {
				valueGenerator = new DecimalNumberGenerator<Number>(fieldType, quantity,
						(DecimalNumberConstraints) constraints);
			}
		} else if (String.class.isAssignableFrom(fieldType)) {
			valueGenerator = new StringGenerator(quantity, (StringConstraints) constraints);
		}
		return (ValueGenerator<E>) valueGenerator;
	}
}
