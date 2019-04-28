package jenerator.engine;

import java.lang.reflect.InvocationTargetException;

import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.validations.GenValidation;
import jenerator.validations.congruence.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

/**
 * This class should be able to offer methods to the user that internally, calls
 * the logic of the API.
 * 
 * @author pablo
 *
 */
public class GeneratorEngine {

	EngineConfiguration engineConfiguration;
	GeneratorController generatorController;

	public GeneratorEngine() {
		engineConfiguration = EngineConfiguration.getInstance();
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

		// For each field set a value
		generatorController = new GeneratorController(instance);
		generatorController.process();
		return instance;
	}
}
