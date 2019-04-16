package jenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import jenerator.validations.GenValidation;
import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class Jenerator {

	public static <T extends Object> T generateEmpty(Class<T> class1) {
		try {
			return class1.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends Object> T generate(Class<T> classgen) throws FieldValidationException, NoEmptyConstructorException, AnnotationMismatchFieldException {
		GenValidation.validate(classgen);
		return null;
	}
	
//	private static <T> boolean hasNecessaryGettersAndSetters(Class<T> class1) {
//		List<Field> declaredFields = Arrays.asList(class1.getDeclaredFields());
//		declaredFields.forEach(field -> {
//			
//		});
//		return false;
//	}

	public static <T extends Object> T getFieldsToGenInfo(Class<T> classgen) {
		Field[] declaredFields = classgen.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Annotation[] annotations = declaredFields[i].getAnnotations();
			for (int j = 0; j < annotations.length; j++) {
				System.out.println(annotations[j].annotationType().getCanonicalName() + " " + declaredFields[i].getName());
//				FieldGenerable annotation = declaredFields[i].getAnnotation(Gene.class);
//				if (annotation != null) {
//					System.out.println("\tdocValue - " + annotation.source() + " \n\tunique - " + annotation.unique());
//				}
			}
		}
		return null;
	}
	
	public static <T extends Object> Set<T> getFieldsToGen(Class<T> classgen) {
		Field[] declaredFields = classgen.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Annotation[] annotations = declaredFields[i].getAnnotations();
			for (int j = 0; j < annotations.length; j++) {
				System.out.println(annotations[j].annotationType().getCanonicalName() + " " + declaredFields[i].getName());
//				FieldGenerable annotation = declaredFields[i].getAnnotation(FieldGenerable.class);
//				if (annotation != null) {
//					System.out.println("\tdocValue - " + annotation.source() + " \n\tunique - " + annotation.unique());
//				}
			}
		}
		return null;
	}
}
