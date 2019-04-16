package jenerator.validations;

import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.congruency.AnnotationsValidation;
import jenerator.validations.pojo.POJOValidation;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class GenValidation {

	public static <T extends Object> void validate(Class<T> class1)
			throws FieldValidationException, NoEmptyConstructorException, AnnotationMismatchFieldException {
		POJOValidation.validate(class1);
		AnnotationsValidation.validate(class1);
	}
}
