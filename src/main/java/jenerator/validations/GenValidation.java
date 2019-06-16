package jenerator.validations;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jenerator.annotations.Generable;
import jenerator.configuration.JeneratorConfiguration;
import jenerator.configuration.filters.GenerableFieldsFilter;
import jenerator.configuration.filters.GenerableFieldsFilter.FieldFilterType;
import jenerator.validations.congruence.FieldCongruenceChecker;
import jenerator.validations.congruence.exceptions.FieldCongruenceException;
import jenerator.validations.congruence.exceptions.CongruenceException;
import jenerator.validations.exceptions.NoGenerableClassException;
import jenerator.validations.exceptions.ValidationException;

public class GenValidation {

	JeneratorConfiguration engineConfiguration = JeneratorConfiguration.getInstance();

	public GenValidation() {
		super();
	}

	public void validate(Class<?> class1, long numInstances) throws ValidationException, CongruenceException {
		if (class1.getAnnotation(Generable.class) != null) {
			POJOValidator.isPOJO(class1);
			FieldFilterType fieldFilter = ((Generable) class1.getAnnotation(Generable.class)).getFieldFilter();
			GenerableFieldsFilter generableFieldsFilter = new GenerableFieldsFilter(fieldFilter);
			List<Field> generableFields = Arrays.asList(class1.getDeclaredFields()).stream()
					.filter(generableFieldsFilter).collect(Collectors.toList());
			FieldCongruenceChecker fieldCongruenceChecker = new FieldCongruenceChecker();
			Optional<Field> field = generableFields.stream().filter(fieldCongruenceChecker).findFirst();
			if (field.isPresent()) {
				throw new CongruenceException(new FieldCongruenceException(field.get()));
			}
		} else {
			throw new NoGenerableClassException(class1);
		}

	}
}
