package jenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jenerator.engine.GeneratorController;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.validations.GenValidation;
import jenerator.validations.congruence.exceptions.CongruenceException;
import jenerator.validations.congruence.exceptions.ValidationException;
import jenerator.validations.pojo.exceptions.POJOValidationException;

public class Jenerator {

	private static JeneratorConfiguration engineConfiguration;
	private static GeneratorController generatorController;

	static {
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

	public static <T extends Object> List<T> generate(Class<T> class1, long numInstances) throws JeneratorException {
		// Validate POJO and congruence
		GenValidation genValidation = new GenValidation(engineConfiguration);
		try {
			genValidation.validate(class1, numInstances);
		} catch (POJOValidationException | CongruenceException e) {
			throw new ValidationException(e);
		}

		// Create n empty instance of that class
		List<T> instances = new ArrayList<T>();
		try {
			instances.add(class1.getConstructor().newInstance());
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}

		// Process each class
		generatorController = new GeneratorController(class1, instances);
		try {
			generatorController.process();
		} catch (ElementFromSourceException | CoverageExceededException e) {
			throw new JeneratorException(e);
		}
		return instances;
	}
}
