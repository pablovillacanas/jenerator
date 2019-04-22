package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.reader.NaturalNumberGeneratorReader;
import jenerator.engine.generators.NaturalNumberGenerator;

public class GeneratorController {

	Object instance;
	List<Field> fields;

	public GeneratorController(Object instance, List<Field> fields) {
		super();
		this.instance = instance;
		this.fields = fields;
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
			NaturalNumberGenerator<Long> nng = new NaturalNumberGenerator<Long>(reader) {
			};
			// Obtenemos lo que queremos
			Long randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			Method[] methods = instance.getClass().getMethods();
			String fieldUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldUpp, Long.class);
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
		for (Field field : fields) {
			setValue(instance, field);
		}
	}
}
