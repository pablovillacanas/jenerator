package jenerator.filters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import jenerator.annotations.JenAnnotations;
import jenerator.annotations.NotGenerable;

public class GenerableFieldsFilter {

	List<Field> fields;
	Predicate<Field> filedFilter;

	public enum Filter {

		/**
		 * This predicate filters all fields that have one annotation of type
		 * jenerator.annotations and does not have a @NoGenerable annotation
		 */
		DEFAULTFILTER,

		/**
		 * This predicate filters all fields that do not have a @NoGenerable annotation
		 */
		LAZYFILTER;
	}

	public GenerableFieldsFilter(List<Field> fields) {
		this(fields, GenerableFieldsFilter.Filter.DEFAULTFILTER);
	}

	public GenerableFieldsFilter(List<Field> fields, GenerableFieldsFilter.Filter filter) {
		this.fields = fields;
		switch (filter) {
		case LAZYFILTER:
			this.filedFilter = new Predicate<Field>() {
				@Override
				public boolean test(Field field) {
					if (field.getAnnotation(NotGenerable.class) == null)
						return true;
					else
						return false;
				}
			};
			break;
		default:
			this.filedFilter = new Predicate<Field>() {
				@Override
				public boolean test(Field field) {
					String packageName = JenAnnotations.getPackageName();
					Annotation[] fieldAnnotations = field.getAnnotations();
					for (int i = 0; i < fieldAnnotations.length; i++) {
						Class<? extends Annotation> class1 = fieldAnnotations[i].annotationType();
						Package package1 = class1.getPackage();
						String name = package1.getName();
						if (name.equals(packageName)) {
							if (fieldAnnotations[i].annotationType() != NotGenerable.class) {
								return true;
							} else {
								return false;
							}
						}
					}
					return false;
				}
			};
			break;
		}
	}

	public List<Field> filter() {
		return fields.stream().filter(filedFilter).collect(Collectors.toList());
	}
}
