package jenerator.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import jenerator.filters.GenerableFieldsFilter;
import jenerator.validations.GenValidation;
import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

//TODO multithread
public class GeneratorEngine {

	public Object generateOne(Class<?> class1)
			throws FieldValidationException, NoEmptyConstructorException, AnnotationMismatchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// validate
		GenValidation.validate(class1);

		// create an empty instance of that class
		Object instance = null;
		try {
			 instance = class1.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// Get generable fields
		List<Field> generableFields = new GenerableFieldsFilter(Arrays.asList(class1.getDeclaredFields())).filter();

		// For each field set a value
		new GeneratorController(instance, generableFields).process();
		return instance;
	}
}
