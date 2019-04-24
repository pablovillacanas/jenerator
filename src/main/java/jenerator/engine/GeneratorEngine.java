package jenerator.engine;

import java.lang.reflect.InvocationTargetException;

import jenerator.filter.exceptions.NotAnnotationEncountered;
import jenerator.validations.GenValidation;
import jenerator.validations.congruence.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

//TODO multithread
public class GeneratorEngine {

	EngineConfiguration engineConfiguration;

	public GeneratorEngine(EngineConfiguration engineConfiguration) {
		this.engineConfiguration = engineConfiguration;
	}

	public Object generateOne(Class<?> class1) throws FieldValidationException, NoEmptyConstructorException,
			AnnotationMismatchFieldException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		// Validate POJO and congruence
		GenValidation.validate(class1);

		// Create an empty instance of that class
		Object instance = null;
		try {
			instance = class1.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Get generable fields

		// For each field set a value
		new GeneratorController(instance, class1).process();
		return instance;
	}
}
