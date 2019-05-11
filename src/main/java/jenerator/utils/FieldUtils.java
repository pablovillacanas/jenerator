package jenerator.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import jenerator.annotations.constraints.CommonConstraints;
import jenerator.annotations.readers.AnnotationReader;
import jenerator.filters.GenerableAnnotationsFilter;
import jenerator.validations.congruence.MinMaxValuesBoundedMaintainer;

public class FieldUtils {

	public static Annotation getGenerableAnnotation(Field field) {
		return (Annotation) Arrays.asList(field.getAnnotations()).stream().filter(new GenerableAnnotationsFilter())
				.findFirst().get();
	}

	public static CommonConstraints getConstraints(Field field) {
		Annotation annotation = getGenerableAnnotation(field);
		AnnotationReader annotationReader = new AnnotationReader();
		CommonConstraints constraints = annotationReader.readValues(annotation);
		return new MinMaxValuesBoundedMaintainer(constraints).apply(field.getType());
	}

	public static Method getterOf(Field field) {
		String fieldName = "";
		try {
			Class<?> declaringClass = field.getDeclaringClass();
			fieldName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			return declaringClass.getMethod("set" + fieldName, field.getType());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
