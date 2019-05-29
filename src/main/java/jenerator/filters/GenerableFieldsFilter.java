package jenerator.filters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Predicate;

import jenerator.JeneratorConfiguration.FieldFilterType;
import jenerator.annotations.GeneratorAnnotations;
import jenerator.annotations.NoGenerable;

/**
 * <p>
 * This class filters the fields of a {@link jenerator.annotation.Generable
 * Generable} class by filter all its fields with a specific FieldFilterType.
 * </p>
 * 
 * @see FieldFilterType
 * 
 * @author pablo
 *
 */
public class GenerableFieldsFilter implements Predicate<Field> {

	FieldFilterType filter;

	public GenerableFieldsFilter(FieldFilterType filter) {
		this.filter = filter;
	}

	@Override
	public boolean test(Field field) {
		switch (filter) {
		case LAZYFILTER:
			if (field.getAnnotation(NoGenerable.class) == null)
				return true;
			else
				return false;
		case EXPLICITFILTER:
			String packageName = GeneratorAnnotations.getPackageName();
			Annotation[] fieldAnnotations = field.getAnnotations();
			for (int i = 0; i < fieldAnnotations.length; i++) {
				Class<? extends Annotation> class1 = fieldAnnotations[i].annotationType();
				Package package1 = class1.getPackage();
				String name = package1.getName();
				if (name.equals(packageName)) {
					if (fieldAnnotations[i].annotationType() != NoGenerable.class) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}
}
