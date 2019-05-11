package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import jenerator.annotations.constraints.CommonConstraints;
import jenerator.annotations.readers.AnnotationReader;
import jenerator.engine.generators.ValueGenerator;
import jenerator.filters.GenerableFieldsFilter;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.utils.ClassUtils;
import jenerator.utils.FieldUtils;

public class GeneratorController {

	Object instance;
	GenerableFieldsFilter generableFieldsFilter;
	AnnotationReader annotationReader;

	public GeneratorController(Object instance) {
		this.instance = instance;
		generableFieldsFilter = new GenerableFieldsFilter(null);
	}

	public void process() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		List<Field> generableFields = ClassUtils.getGenerableFields(instance.getClass());
		for (Field field : generableFields) {
			annotationDispatcher(field);
		}
	}

	private void annotationDispatcher(Field field) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		CommonConstraints constraints = FieldUtils.getConstraints(field);
		setValue(instance, field, constraints);
	}

	private void setValue(Object instance, Field field, CommonConstraints constraints) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = FieldUtils.getterOf(field);
		Object value = ValueGenerator.getValue(field.getType(), constraints);
		if (Long.class.isAssignableFrom(field.getType())) {
			method.invoke(instance, value);
		}else if (Integer.class.isAssignableFrom(field.getType())) {
			method.invoke(instance, (int) (long) value);
		}else if (Short.class.isAssignableFrom(field.getType())) {
			method.invoke(instance, (short) (long) value);
		}else if (Byte.class.isAssignableFrom(field.getType())) {
			method.invoke(instance, (byte) (long) value);
		} else if (String.class.isAssignableFrom(field.getType())) {
			method.invoke(instance, String.class.cast(value));
		}
	}
}
