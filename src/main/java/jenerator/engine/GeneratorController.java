package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.ValueGenerator;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.utils.ClassUtils;
import jenerator.utils.FieldUtils;

/**
 * <p>
 * This class populates a list of instance.
 * </p>
 * 
 * @author pablo
 *
 */
public class GeneratorController {

	private List<? extends Object> instances;
	private Class<?> actualClass;
	private Map<Method, ValueGenerator<? extends Object>> relation;

	public GeneratorController(Class<?> class1, List<? extends Object> instances) {
		this.instances = instances;
		this.actualClass = class1;
		relation = new HashMap<Method, ValueGenerator<? extends Object>>();

	}

	public void process() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		List<Field> generableFields = ClassUtils.getGenerableFields(actualClass);
		for (Field field : generableFields) {
			Method getter = FieldUtils.getterOf(field);
			ValueGenerator<? extends Object> generatedValues = generateValues(field.getType(),
					FieldUtils.getConstraints(field));
			relation.put(getter, generatedValues);
		}
		for (Object t : instances) {
			relation.forEach((m, vg) -> {
				try {
					m.invoke(t, m.getParameterTypes()[0].getClass().cast(vg.getValue()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		}
	}

	@SuppressWarnings("unchecked")
	private <E extends Object> ValueGenerator<? extends E> generateValues(Class<E> fieldType,
			Constraints constraints) {
		ValueGenerator<? extends Object> valueGenerator = null;
		if (Number.class.isAssignableFrom(fieldType)) {
			if (Long.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)
					|| Short.class.isAssignableFrom(fieldType) || Byte.class.isAssignableFrom(fieldType)) {
				valueGenerator = new NaturalNumberGenerator((NaturalNumberConstraints) constraints);
			} else {
//						new DecimalNumberGenerator().getValue(field.getType(), constraints);
			}
		} else if (fieldType.isAssignableFrom(Long.class)) {
//				new StringGenerator((StringConstraints) constraints).generate(instances.size());
		}
		return (ValueGenerator<? extends E>) valueGenerator;
	}
}
