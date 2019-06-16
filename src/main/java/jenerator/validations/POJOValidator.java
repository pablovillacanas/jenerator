package jenerator.validations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jenerator.validations.exceptions.FieldValidationException;
import jenerator.validations.exceptions.NoEmptyConstructorException;
import jenerator.validations.exceptions.POJOValidationException;

/**
 * <p>
 * This class ensure that another one is a bean or a POJO (Plain Old Java
 * Object).
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class POJOValidator {

	public static <T extends Object> boolean isPOJO(Class<T> class1) throws POJOValidationException {
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
	 * @param <T>    type parameter of the class
	 * @param class1 the class tested to be encapsulated.
	 * @return true if has a valid constructor, false otherwise.
	 * @throws POJOValidationException if class not have a correct non-arg
	 *                                 constructor declared.
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Object> boolean hasValidConstructor(Class<T> class1) throws POJOValidationException {
		Constructor<T>[] constructors = (Constructor<T>[]) class1.getConstructors();
		for (Constructor<T> c : constructors) {
			int parameterCount = c.getParameterCount();
			if (parameterCount == 0)
				return true;
		}
		throw new POJOValidationException(
				new NoEmptyConstructorException("Class " + class1 + " must declare a public non-param constructor"));
	}

	/**
	 * <p>
	 * This method filters all private fields that do not have final or static
	 * modifiers and ensures that have a setter and a getter.
	 * </p>
	 * 
	 * @param <T>    type parameter of the class
	 * @param class1 the class tested to be encapsulated.
	 * @return true if fields are encapsulated, false otherwise
	 * @throws POJOValidationException if class have not some field without proper
	 *                                 getters and setters.
	 */
	public static <T extends Object> boolean isEncapsulated(Class<T> class1) throws POJOValidationException {
		List<Field> privateFilteredFields = getDeclaredFields(class1).stream().filter(field -> {
			int modifiers = field.getModifiers();
			// If field is private and it is not final or static, it must have a setter and
			// a getter.
			if (!field.isSynthetic() && Modifier.isPrivate(modifiers) && !Modifier.isFinal(modifiers)
					|| !Modifier.isStatic(modifiers)) {
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
	 * Gets only the fields of the instance.
	 * </p>
	 * 
	 * @param <T>    type parameter of the class
	 * @param class1 the class where fields generable come from.
	 * @return a list of fields declared by the Class
	 */
	private static <T extends Object> ArrayList<Field> getDeclaredFields(Class<T> class1) {
		return (ArrayList<Field>) Arrays.asList(class1.getDeclaredFields()).stream()
				.filter(field -> !field.getName().startsWith("this$")).collect(Collectors.toList());
	}

	private static boolean hasGettersAndSetters(final Field field) throws POJOValidationException {
		try {
			int size = Arrays.asList(field.getDeclaringClass().getMethods()).stream().filter(method -> {
				return (isGetter(method, field.getName()) || isSetter(method, field.getName()));
			}).collect(Collectors.toSet()).size();
			if (size != 2)
				throw new POJOValidationException(new FieldValidationException(
						"Field " + field.getName() + " must declare proper getters and setters"));
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
