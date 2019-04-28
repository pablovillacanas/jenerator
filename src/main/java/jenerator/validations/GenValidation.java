package jenerator.validations;

import java.util.Arrays;

import jenerator.validations.congruence.CongruenceChecker;
import jenerator.validations.congruence.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.POJOValidator;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class GenValidation {

	public static <T extends Object> void validate(Class<T> class1)
			throws FieldValidationException, NoEmptyConstructorException, AnnotationMismatchFieldException {
		POJOValidator.isPOJO(class1); // Its a POJO
		CongruenceChecker.check(Arrays.asList(class1.getDeclaredFields())); // Fields annotated has correct congruence
	}
}
