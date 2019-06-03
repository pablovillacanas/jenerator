package jenerator.validations;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import jenerator.JeneratorConfiguration;
import jenerator.utils.ClassUtils;
import jenerator.validations.congruence.FieldCongruenceChecker;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;
import jenerator.validations.congruence.exceptions.CongruenceException;
import jenerator.validations.pojo.POJOValidator;
import jenerator.validations.pojo.exceptions.POJOValidationException;

public class GenValidation {

	JeneratorConfiguration engineConfiguration;

	public GenValidation(JeneratorConfiguration engineConfiguration) {
		super();
		this.engineConfiguration = engineConfiguration;
	}

	public void validate(Class<?> class1, long numInstances) throws POJOValidationException, CongruenceException {
		POJOValidator.isPOJO(class1);
		List<Field> generableFields = ClassUtils.getGenerableFields(class1);
		Optional<Field> field = generableFields.stream().filter(new FieldCongruenceChecker()).findFirst();
		if (field.isPresent()) {
			throw new CongruenceException(new Annotation_FieldCongruenceException(field.get()));
		}
	}
}
