package jenerator.validations;

import java.lang.reflect.Field;
import java.util.List;

import jenerator.JeneratorConfiguration;
import jenerator.filters.UniqueFieldsFilter;
import jenerator.utils.ClassUtils;
import jenerator.validations.congruence.checkers.FieldCongruenceChecker;
import jenerator.validations.congruence.checkers.UniqueCongruenceChecker;
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

	public void validate(Class<?> class1, long numInstances)
			throws FieldValidationException, NoEmptyConstructorException, Annotation_FieldCongruenceException {
		POJOValidator.isPOJO(class1);
		List<Field> generableFields = ClassUtils.getGenerableFields(class1);
		if (generableFields.stream().allMatch(new FieldCongruenceChecker())) {
			if (generableFields.stream().filter(new UniqueFieldsFilter())
					.allMatch(new UniqueCongruenceChecker(numInstances))) {

			} else {
				// Throw uniquness imposible
			}
			// Thow incongruence annotation-filter
		}

	}

//	private void checkField_AnnotationCongruence(List<Field> fields) throws Annotation_FieldCongruenceException {
//		new CongruenceAnnotation_FieldChecker().
//	}
}
