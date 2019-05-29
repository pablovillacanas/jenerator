package jenerator.validations.pojo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

/**
 * <p>
 * Validates that class is a POJO
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class POJOValidator {

	/**
	 * <p>
	 * Check if the class has a valid constructor and it fields are encapsulated
	 * </p>
	 * 
	 * @param <T>
	 * @param class1
	 * @return true if it is a POJO, false otherwise
	 * @throws FieldValidationException
	 * @throws NoEmptyConstructorException
	 */
	public static <T extends Object> boolean isPOJO(Class<T> class1)
			throws FieldValidationException, NoEmptyConstructorException {
		if (hasValidConstructor(class1) && isEncapsulated(class1))
			return true;
		else
			return false;
	}

	/**
	 * <p>
	 * Checks if the class has declared or has a default empty-args constructor.
	 * </p>
	 * <p>
	 * Note that inner classes must declare explicitly the non-args constructor
	 * because due to have the parent class reference, the compiler default
	 * constructor has one argument that refers to that parent.
	 * </p>
	 * 
	 * @param <T>
	 * @param class1
	 * @return
	 * @throws NoEmptyConstructorException
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Object> boolean hasValidConstructor(Class<T> class1) throws NoEmptyConstructorException {
		Constructor<T>[] constructors = (Constructor<T>[]) class1.getConstructors();
		for (Constructor<T> c : constructors) {
			int parameterCount = c.getParameterCount();
			if (parameterCount == 0)
				return true;
		}
		throw new NoEmptyConstructorException("Class " + class1 + " must declare a public non-param constructor");
	}

	/**
	 * <p>
	 * This method filters all private fields that do not have final or static
	 * modifiers and ensures that have a setter and a getter
	 * </p>
	 * 
	 * @param <T>
	 * @param class1
	 * @return
	 * @throws FieldValidationException
	 */
	public static <T extends Object> boolean isEncapsulated(Class<T> class1) throws FieldValidationException {
		List<Field> privateFilteredFields = getDeclaredFields(class1).stream().filter(field -> {
			int modifiers = field.getModifiers();
			// If field is private and it is not final or static, it must have a setter and
			// a getter.
			if (Modifier.isPrivate(modifiers) && !Modifier.isFinal(modifiers) || !Modifier.isStatic(modifiers)) {
				return true;
			} else {
				return false;
			}
		}).collect(Collectors.toList());
		// Filter only private non-final ones
		for (Field field : privateFilteredFields) {
			hasGettersAndSetters(field);
		}
		return true;
	}

	/**
	 * <p>
	 * Filters only the fields that are declared in that class, excluding inherited
	 * ones.
	 * </p>
	 * 
	 * @param <T>
	 * @param class1
	 * @return
	 */
	private static <T extends Object> ArrayList<Field> getDeclaredFields(Class<T> class1) {
		return (ArrayList<Field>) Arrays.asList(class1.getDeclaredFields()).stream()
				.filter(field -> !field.getName().startsWith("this$")).collect(Collectors.toList());
	}

	/**
	 * <p>
	 * Compares all the methods declared in the class with the getter and setter
	 * that there would have that field.
	 * </p>
	 * 
	 * @param field
	 * @return true if field has setter and getter
	 * @throws FieldValidationException
	 */
	private static boolean hasGettersAndSetters(final Field field) throws FieldValidationException {
		try {
			int size = Arrays.asList(field.getDeclaringClass().getMethods()).stream().filter(method -> {
				return (isGetter(method, field.getName()) || isSetter(method, field.getName()));
			}).collect(Collectors.toSet()).size();
			if (size != 2)
				throw new FieldValidationException();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean isGetter(Method method, String fieldname) {
		fieldname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
			if (method.getName().matches("^get" + fieldname + "$") && !method.getReturnType().equals(void.class))
				return true;
			if (method.getName().matches("^is" + fieldname + "$") && method.getReturnType().equals(boolean.class))
				return true;
		}
		return false;
	}

	private static boolean isSetter(Method method, String fieldname) {
		fieldname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
		return Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(void.class)
				&& method.getParameterTypes().length == 1 && method.getName().matches("^set" + fieldname + "$");
	}
}
