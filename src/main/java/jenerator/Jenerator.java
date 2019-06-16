package jenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jenerator.engine.GeneratorController;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.validations.GenValidation;
import jenerator.validations.congruence.exceptions.CongruenceException;
import jenerator.validations.exceptions.POJOValidationException;
import jenerator.validations.exceptions.ValidationException;

public class Jenerator {

	private static GeneratorController generatorController;

	/**
	 * <p>
	 * Generates one empty instances of an Object you specify.
	 * </p>
	 * 
	 * @param <T>    Object type you desire to generate
	 * @param class1 Class of the object you desire to generate
	 * @return An empty object you request.
	 */
	public static <T extends Object> T generateEmpty(Class<T> class1) {
		try {
			return class1.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Generates n empty instances of an Object you specify.
	 * </p>
	 * 
	 * @param <T>          Object type you desire to generate
	 * @param class1       Class of the object you desire to generate
	 * @param numInstances of the object you desire to generate
	 * @return A list of size you desire of objects you request.
	 */
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

	/**
	 * <p>
	 * Generates n empty instances of an Object you specify.
	 * </p>
	 * <p>
	 * Be aware of how you have annotated the instance and how (if setted)
	 * constraints configuration may be applied.
	 * </p>
	 * 
	 * @param <T>          Object type you desire to generate
	 * @param class1       Class of the object you desire to generate
	 * @param numInstances of the object you desire to generate
	 * @throws JeneratorException if there was an error during the generation
	 * @return A list of size you desire of objects you request.
	 */
	public static <T extends Object> List<T> generate(Class<T> class1, long numInstances) throws JeneratorException {
		// Validate POJO and congruence
		GenValidation genValidation = new GenValidation();
		try {
			genValidation.validate(class1, numInstances);
		} catch (POJOValidationException | CongruenceException e) {
			throw new ValidationException(e);
		}

		List<T> instances = new ArrayList<T>();
		try {
			instances.add(class1.getConstructor().newInstance());
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}

		// Process each class
		generatorController = new GeneratorController(instances);
		try {
			generatorController.process();
		} catch (ElementFromSourceException | CoverageExceededException e) {
			throw new JeneratorException(e);
		}
		return instances;
	}

	/**
	 * <p>
	 * Generates one single instances of an Object you specify.
	 * </p>
	 * <p>
	 * Be aware of how you have annotated the instance and how (if setted)
	 * constraints configuration may be applied.
	 * </p>
	 * 
	 * @param <T>    Object type you desire to generate
	 * @param class1 Class of the object you desire to generate
	 * @return A list of size you desire of objects you request.
	 * @throws JeneratorException if there was an error during the generation
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T generateOne(Class<?> class1) throws JeneratorException {
		return (T) generate(class1, 1).get(0);
	}
}
