package jenerator.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import jenerator.filters.GenerableAnnotationsFilter;

public class FieldUtils {

	public static Annotation getGenerableAnnotation(Field field) {
		return (Annotation) Arrays.asList(field.getAnnotations()).stream().filter(new GenerableAnnotationsFilter())
				.findFirst().get();
	}

	public static Method getterOf(Field field) {
		String methodName = "";
		try {
			Class<?> declaringClass = field.getDeclaringClass();
			methodName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			return declaringClass.getMethod("set" + methodName, field.getType());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field fieldOf(Method method) {
		String name = method.getName();
		String fieldName = name.substring(3, name.length()).substring(0, 1).toLowerCase() + name.substring(4);
		try {
			return method.getDeclaringClass().getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}
}
