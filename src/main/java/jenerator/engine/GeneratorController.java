package jenerator.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jenerator.Jenerator;
import jenerator.JeneratorException;
import jenerator.annotations.Generable;
import jenerator.annotations.constraints.BooleanConstraints;
import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.AnnotationParser;
import jenerator.configuration.JeneratorConfiguration;
import jenerator.configuration.filters.GenerableAnnotationsFilter;
import jenerator.configuration.filters.GenerableFieldsFilter;
import jenerator.configuration.filters.GenerableFieldsFilter.FieldFilterType;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.BooleanGenerator;
import jenerator.engine.generators.DecimalNumberGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.StringGenerator;
import jenerator.engine.generators.ValueGenerator;
import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;

/**
 * <p>
 * This class populates a list of instances.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class GeneratorController {

	private JeneratorConfiguration engineConfiguration = JeneratorConfiguration.getInstance();
	private final AnnotationParser annotationParser = new AnnotationParser();
	private List<? extends Object> instances;
	private Map<Method, Collection<? extends Object>> method_values;

	public GeneratorController(List<? extends Object> instances) {
		this.instances = instances;
		method_values = new HashMap<Method, Collection<? extends Object>>();
	}

	public void process() throws CoverageExceededException, NoSuitableElementsOnSource, ElementFromSourceException {
		List<Field> generableFields = getGenerableFields(instances.get(0).getClass());
		int quantity = instances.size();
		for (Field field : generableFields) {
			Method getter = retrieveGetterOf(field);
			Class<?> fieldType = field.getType();
			Optional<Annotation> generableAnnotation = getGenerableAnnotation(field);
			Constraints constraints = null;
			if (generableAnnotation.isPresent()) {
				constraints = annotationParser.parse(generableAnnotation.get());
			}
			Collection<? extends Object> generatedValues = generateValues(fieldType, quantity, constraints);
			method_values.put(getter, generatedValues);
		}
		for (Object t : instances) {
			method_values.forEach((method, values) -> {
				try {
					Class<?> type = method.getParameterTypes()[0];
					Object value = values.iterator().next();
					method.invoke(t, type.cast(value));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		}
	}

	private Method retrieveGetterOf(Field field) {
		String methodName = "";
		try {
			Class<?> declaringClass = field.getDeclaringClass();
			methodName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			return declaringClass.getMethod("set" + methodName, field.getType());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Field> getGenerableFields(Class<?> actualClass) {
		FieldFilterType fieldFilter = ((Generable) actualClass.getAnnotation(Generable.class)).getFieldFilter();
		GenerableFieldsFilter generableFieldsFilter = new GenerableFieldsFilter(fieldFilter);
		return Arrays.asList(actualClass.getDeclaredFields()).stream().filter(generableFieldsFilter)
				.collect(Collectors.toList());
	}

	private Optional<Annotation> getGenerableAnnotation(Field field) {
		GenerableAnnotationsFilter generableFieldsFilter = engineConfiguration.getGenerableAnnotationFilter();
		return Arrays.asList(field.getAnnotations()).stream().filter(generableFieldsFilter).findFirst();
	}

	@SuppressWarnings({ "unchecked" })
	private <E extends Object> Collection<? extends E> generateValues(Class<?> fieldType, long quantity,
			Constraints constraints) throws CoverageExceededException, ElementFromSourceException {
		ValueGenerator<? extends Object> valueGenerator = null;
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
		} else if (Boolean.class.isAssignableFrom(fieldType)) {
			valueGenerator = new BooleanGenerator(quantity, (BooleanConstraints) constraints);
		} else if (Collection.class.isAssignableFrom(fieldType)) {
			// TODO creamos un nuevo Generator controller solo si su parametrizada es
			// Generable o un tipo soportado por el framework
		} else if (fieldType.isAnnotationPresent(Generable.class)) {
			System.out.println(fieldType.getCanonicalName());
			try {
				Class<?> forName = Class.forName(fieldType.getCanonicalName());
				try {
					return (Collection<? extends E>) Jenerator.generate(forName, quantity);
				} catch (JeneratorException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return (Collection<? extends E>) valueGenerator.generate();
	}

}
