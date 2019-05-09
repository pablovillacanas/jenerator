package jenerator.validations;

import java.lang.reflect.Field;
import java.util.List;

import jenerator.JeneratorConfiguration;
import jenerator.utils.ClassUtils;
import jenerator.validations.congruence.checkers.CongruenceAnnotation_FieldChecker;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;
import jenerator.validations.pojo.POJOValidator;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class GenValidation {

	JeneratorConfiguration engineConfiguration;

	public GenValidation(JeneratorConfiguration engineConfiguration) {
		super();
		this.engineConfiguration = engineConfiguration;
	}

	public void validate(Class<?> class1)
			throws FieldValidationException, NoEmptyConstructorException, Annotation_FieldCongruenceException {
		POJOValidator.isPOJO(class1); // Its a POJO

		List<Field> generableFields = ClassUtils.getGenerableFields(class1);

		checkField_AnnotationCongruence(generableFields);
	}

	private void checkField_AnnotationCongruence(List<Field> fields) throws Annotation_FieldCongruenceException {
		CongruenceAnnotation_FieldChecker checker = new CongruenceAnnotation_FieldChecker();
		for (Field field : fields) {
			checker.ckeck(field);
		}
	}
}
