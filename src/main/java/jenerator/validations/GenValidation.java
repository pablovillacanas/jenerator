package jenerator.validations;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import jenerator.validations.congruence.CongruenceChecker;
import jenerator.validations.congruence.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.POJOValidation;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class GenValidation {

	public static <T extends Object> void validate(Class<T> class1)
			throws FieldValidationException, NoEmptyConstructorException, AnnotationMismatchFieldException {
		POJOValidation.validate(class1);
		List<Field> declaredFields = Arrays.asList(class1.getDeclaredFields());
		CongruenceChecker.check(declaredFields);
	}
}
