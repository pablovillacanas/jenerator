package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import jenerator.annotations.constraints.Constraints;
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
		Constraints constraints = FieldUtils.getConstraints(field);
		setValue(instance, field, constraints);
	}

	private void setValue(Object instance, Field field, Constraints constraints) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = FieldUtils.getterOf(field);
		Object value = ValueGenerator.getValue(field.getType(), constraints);
		if (Long.class.isAssignableFrom(field.getType())) {
			method.invoke(instance, value);
		}
		if (field.getType().isAssignableFrom(Integer.class)) {
			method.invoke(instance, (int) (long) value);
		}
		if (field.getType().isAssignableFrom(Short.class)) {
			method.invoke(instance, (short) (long) value);
		}
		if (field.getType().isAssignableFrom(Byte.class)) {
			method.invoke(instance, (byte) (long) value);
		}
		if (field.getType().isAssignableFrom(String.class)) {
			method.invoke(instance, String.class.cast(value));
		}
	}
}
