package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.AnnotationReader;
import jenerator.engine.generators.ByteGenerator;
import jenerator.engine.generators.IntegerGenerator;
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.ShortGenerator;
import jenerator.engine.generators.StringGenerator;
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
		ValueGenerator<?> vg = null;
		// TODO ValueGenerator(cLASS, constraints).getValue();
		if (field.getType().isAssignableFrom(Long.class)) {
			vg = new LongGenerator((NaturalNumberConstraints) constraints);
			method.invoke(instance, (Long) vg.getValue());
		}
		if (field.getType().isAssignableFrom(Integer.class)) {
			vg = new IntegerGenerator((NaturalNumberConstraints) constraints);
			method.invoke(instance, (Integer) vg.getValue());
		}
		if (field.getType().isAssignableFrom(Short.class)) {
			vg = new ShortGenerator((NaturalNumberConstraints) constraints);
			method.invoke(instance, (Short) vg.getValue());
		}
		if (field.getType().isAssignableFrom(Byte.class)) {
			vg = new ByteGenerator((NaturalNumberConstraints) constraints);
			method.invoke(instance, (Byte) vg.getValue());
		}
		if (field.getType().isAssignableFrom(String.class)) {
			vg = new StringGenerator((StringConstraints) constraints);
			method.invoke(instance, (String) vg.getValue());
		}
	}
}
