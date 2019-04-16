package jenerator.validations.pojo;

import static jenerator.validations.pojo.MethodValidation.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import jenerator.validations.pojo.exceptions.FieldValidationException;

public class FieldValidation {

	public static void hasGettersAndSetters(final Field field) throws FieldValidationException {
		try {
			int size = Arrays.asList(field.getDeclaringClass().getMethods()).stream().filter(method -> {
				return (isGetter(method, field.getName()) || isSetter(method, field.getName()));
			}).collect(Collectors.toSet()).size();
			if (size != 2)
				throw new FieldValidationException();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static boolean hasImposibleConstraints() {
		return false;
	}
}
