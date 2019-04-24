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
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.filter.exceptions.NotAnnotationEncountered;
import jenerator.filters.GenerableAnnotationsFilter;
import jenerator.filters.GenerableFieldsFilter;

public class GeneratorController {

	Object instance;
	Class<?> class1;

	public GeneratorController(Object instance, Class<?> class1) {
		super();
		this.instance = instance;
		this.class1 = class1;
	}

	public void annotationDispatcher(Field field) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		// Filtramos las anotaciones de nuestro framework
		Annotation annotation = GenerableAnnotationsFilter.retrieveGenerableAnnotation(field);
		// Obtenemos las constraints con el Reader
		Constraints constraints = new AnnotationReader().readValues(annotation);
		// Si annotation es de tipo NaturalNumber, le pasamos dicho campo al método
		// encargado de setearle el valor.
		setValue(instance, field, constraints);
	}

	private void setValue(Object instance, Field field, Constraints constraints) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (field.getType().isAssignableFrom(Long.class)) {
			NaturalNumberGenerator<Long> nng = new LongGenerator((NaturalNumberConstraints) constraints);
			// Obtenemos lo que queremos
			Long randomValue = nng.getRandomValue();
			// Se lo metemos al campo
			String fieldFirstUpp = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = instance.getClass().getMethod("set" + fieldFirstUpp, Long.class);
			method.invoke(instance, randomValue);
		}
//			case "java.lang.Integer":
//				break;
//			case "java.lang.Byte":
//				break;
//			case "java.lang.Short":
//				break;
//			default:
//				break;
//			}

	}

	public void process() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		List<Field> generableFields = new GenerableFieldsFilter(Arrays.asList(class1.getDeclaredFields())).filter();
		for (Field field : generableFields) {
			annotationDispatcher(field);
		}
	}
}
