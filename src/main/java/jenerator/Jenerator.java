package jenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jenerator.engine.GeneratorController;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.validations.GenValidation;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

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

	public static <T extends Object> List<T> generate(Class<T> class1, long numInstances)
			throws FieldValidationException, NoEmptyConstructorException, Annotation_FieldCongruenceException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			SecurityException, NotAnnotationEncountered, CoverageExceededException, FileNotFoundException, IOException, NoSuitableElementsOnSource {
		// Validate POJO and congruence
		GenValidation genValidation = new GenValidation(engineConfiguration);
		genValidation.validate(class1, numInstances);

		// Create n empty instance of that class
		List<T> instances = new ArrayList<T>();
		try {
			instances.add(class1.getConstructor().newInstance());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Process each class
		generatorController = new GeneratorController(class1, instances);
		generatorController.process();
		return instances;
	}
}
