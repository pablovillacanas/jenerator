package jenerator.filters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Predicate;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.readers.AnnotationReader;
import jenerator.utils.FieldUtils;

public class UniqueFieldsFilter implements Predicate<Field> {

	@Override
	public boolean test(Field field) {
		Annotation generableAnnotation = FieldUtils.getGenerableAnnotation(field);
		Constraints constraints = new AnnotationReader().readValues(generableAnnotation);
		if (constraints.getUnique()) {
			return true;
		}
		return false;
	}

}
