package jenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jenerator.engine.GeneratorController;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.validations.GenValidation;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class Jenerator {

	private static JeneratorConfiguration engineConfiguration;
	private static GeneratorController generatorController;
	
	static{
		engineConfiguration = JeneratorConfiguration.getInstance();
	}

	public static <T extends Object> T generateEmpty(Class<T> class1) {
		try {
			return class1.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends Object> List<T> generateEmpty(Class<T> class1, int numInstances) {
		List<T> instances = new ArrayList<T>();
		try {
			instances.add(class1.getConstructor().newInstance());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return instances;
	}

	public static <T extends Object> T generateOne(Class<T> class1)
			throws FieldValidationException, NoEmptyConstructorException, Annotation_FieldCongruenceException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			SecurityException, NotAnnotationEncountered {
		// Validate POJO and congruence
		GenValidation genValidation = new GenValidation(engineConfiguration);
		genValidation.validate(class1);

		// Create an empty instance of that class
		T instance = null;
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
