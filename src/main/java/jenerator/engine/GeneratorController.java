package jenerator.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.AnnotationReader;
import jenerator.annotations.readers.exceptions.AnnotationConstraintsException;
import jenerator.engine.generators.ByteGenerator;
import jenerator.engine.generators.IntegerGenerator;
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.ShortGenerator;
import jenerator.engine.generators.StringGenerator;
import jenerator.engine.generators.ValueGenerator;
import jenerator.filters.GenerableAnnotationsFilter;
import jenerator.filters.GenerableFieldsFilter;
import jenerator.filters.exceptions.NotAnnotationEncountered;

public class GeneratorController {

	Object instance;
	GenerableFieldsFilter generableFieldsFilter;
	AnnotationReader annotationReader = new AnnotationReader();

	public GeneratorController(Object instance) {
		this.instance = instance;
		generableFieldsFilter = new GenerableFieldsFilter();
	}

	public void process() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered, AnnotationConstraintsException {
		List<Field> generableFields = Arrays.asList(instance.getClass().getDeclaredFields()).stream()
				.filter(generableFieldsFilter).collect(Collectors.toList());
		for (Field field : generableFields) {
			annotationDispatcher(field);
		}
	}

	private void annotationDispatcher(Field field)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			SecurityException, NotAnnotationEncountered, AnnotationConstraintsException {
		Constraints constraints = getGenerableConstraints(field);
		setValue(instance, field, constraints);
	}

	private Constraints getGenerableConstraints(Field field)
			throws NotAnnotationEncountered, AnnotationConstraintsException {
		Annotation annotation = GenerableAnnotationsFilter.retrieveGenerableAnnotation(field);
		Constraints constraints = annotationReader.readValues(annotation);
		return constraints;
	}

	private void setValue(Object instance, Field field, Constraints constraints) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		Class<?> type = field.getType();
		Method method = instance.getClass().getMethod("set" + fieldFirstUpp, type);
		ValueGenerator<? extends Object> vg;

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
