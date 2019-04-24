package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.reader.NaturalNumberGeneratorReader;
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.filters.GenerableFieldsFilter;

public class GeneratorController {

	Object instance;
	Class<?> class1;

	public GeneratorController(Object instance, Class<?> class1) {
		super();
		this.instance = instance;
		this.class1 = class1;
	}

	public void setValue(Object instance, Field field) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// Se lo pasamos al generador
		switch (field.getType().getCanonicalName()) {
		case "java.lang.Long":
			// Obtenemos la anotación del campo
			NaturalNumberGenerable annotation = field.getAnnotation(NaturalNumberGenerable.class);
			// Obtenemos el objeto Reader
			NaturalNumberGeneratorReader reader = new NaturalNumberGeneratorReader(annotation);
			// Se lo pasamos al generador para que haga su magia. Magia negra.
			NaturalNumberGenerator<Long> nng = new LongGenerator(reader);
			// Obtenemos lo que queremos
			Long randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldFirstUpp, Long.class);
			method.invoke(instance, randomValue);
			break;
		case "java.lang.Integer":
			break;
		case "java.lang.Byte":
			break;
		case "java.lang.Short":
			break;
		case "java.lang.Double":
			break;
		case "java.lang.Float":
			break;
		}
	}

	public void process() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		List<Field> generableFields = new GenerableFieldsFilter(Arrays.asList(class1.getDeclaredFields())).filter();
		for (Field field : generableFields) {
			setValue(instance, field);
		}
	}
}
