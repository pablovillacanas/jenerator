package jenerator.validations.pojo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jenerator.exceptions.FieldValidationException;
import jenerator.exceptions.NoEmptyConstructorException;

public class POJOValidation {

	public static <T extends Object> void validate(Class<T> class1)
			throws FieldValidationException, NoEmptyConstructorException {
		validateConstructor(class1);
		validateFields(class1);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Object> void validateConstructor(Class<T> class1) throws NoEmptyConstructorException {
		Constructor<T>[] constructors = (Constructor<T>[]) class1.getConstructors();
		for (Constructor<T> c : constructors) {
			int parameterCount = c.getParameterCount();
			if (parameterCount == 1)
				return;
		}
		throw new NoEmptyConstructorException("Class " + class1 + " must explicitly declare a public non-param constructor");
	}

	public static <T> void validateFields(Class<T> class1) throws FieldValidationException {
		List<Field> fields = (List<Field>) Arrays.asList(class1.getDeclaredFields()).stream().filter(field -> !field.getName().startsWith("this$")).collect(Collectors.toList());
		for (Field field : fields) {
			FieldValidation.hasGettersAndSetters(field);
		}
	}
}
