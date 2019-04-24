package jenerator.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import jenerator.annotations.reader.AnnotationReader;
import jenerator.annotations.reader.Constraints;
import jenerator.annotations.reader.NaturalNumberConstraints;
import jenerator.engine.generators.ByteGenerator;
import jenerator.engine.generators.IntegerGenerator;
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.ShortGenerator;
import jenerator.filters.GenerableAnnotationsFilter;
import jenerator.filters.GenerableFieldsFilter;
import jenerator.filters.exceptions.NotAnnotationEncountered;

public class GeneratorController {

	Object instance;
	Class<?> class1;

	public GeneratorController(Object instance, Class<?> class1) {
		super();
		this.instance = instance;
		this.class1 = class1;
	}

	private void annotationDispatcher(Field field) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		// Filtramos las anotaciones de nuestro framework
		Annotation annotation = GenerableAnnotationsFilter.retrieveGenerableAnnotation(field);
		// Obtenemos las constraints con el Reader
		Constraints constraints = new AnnotationReader().readValues(annotation);
		// Si annotation es de tipo NaturalNumber, le pasamos dicho campo al método
		// encargado de setearle el valor.
		setValue(instance, field, constraints);
	}

	private void setValue(Object instance, Field field, Constraints constraints) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (field.getType().isAssignableFrom(Long.class)) {
			NaturalNumberGenerator<Long> nng = new LongGenerator((NaturalNumberConstraints) constraints);
			// Obtenemos lo que queremos
			Long randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldFirstUpp, Long.class);
			method.invoke(instance, randomValue);
		}
		if (field.getType().isAssignableFrom(Integer.class)) {
			NaturalNumberGenerator<Integer> nng = new IntegerGenerator((NaturalNumberConstraints) constraints);
			// Obtenemos lo que queremos
			Integer randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldFirstUpp, Integer.class);
			method.invoke(instance, randomValue);
		}
		if (field.getType().isAssignableFrom(Short.class)) {
			NaturalNumberGenerator<Short> nng = new ShortGenerator((NaturalNumberConstraints) constraints);
			// Obtenemos lo que queremos
			Short randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldFirstUpp, Short.class);
			method.invoke(instance, randomValue);
		}
		if (field.getType().isAssignableFrom(Byte.class)) {
			NaturalNumberGenerator<Byte> nng = new ByteGenerator((NaturalNumberConstraints) constraints);
			// Obtenemos lo que queremos
			Byte randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldFirstUpp, Byte.class);
			method.invoke(instance, randomValue);
		}
	}

	public void process() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		List<Field> generableFields = new GenerableFieldsFilter(Arrays.asList(class1.getDeclaredFields())).filter();
		for (Field field : generableFields) {
			annotationDispatcher(field);
		}
	}
}
