package jenerator.configuration.filters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Predicate;

import jenerator.annotations.JenAnnotations;
import jenerator.annotations.NoGenerable;

public class GenerableFieldsFilter implements Predicate<Field> {

	public enum FieldFilterType {

		/**
		 * <p>
		 * This predicate filters all fields that have one annotation of type
		 * jenerator.annotations and does not have a
		 * {@link jenerator.annotation.NoGenerable NoGenerable} annotation
		 * </p>
		 */
		EXPLICITFILTER,

		/**
		 * <p>
		 * This predicate filters all fields that do not have a
		 * {@link jenerator.annotation.NoGenerable NoGenerable} annotation and could be
		 * generated. See the list of fields that could be generated at
		 * {@link jenerator.validations.congruence.FieldCongruenceChecker.ConcordantAnnotationRetriever
		 * RetrieveConcordantAnnotation}
		 * </p>
		 */
		LAZYFILTER;
	}

	FieldFilterType filter;

	public GenerableFieldsFilter(FieldFilterType filter) {
		super();
		this.filter = filter;
	}

	@Override
	public boolean test(Field field) {
		if (!field.isSynthetic()) {
			switch (filter) {
			case LAZYFILTER:
				if (field.getAnnotation(NoGenerable.class) == null)
					return true;
				else
					return false;
			case EXPLICITFILTER:
				String packageName = JenAnnotations.getPackageName();
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
		}
		return false;
	}

	public FieldFilterType getFilter() {
		return filter;
	}

	public void setFilterType(FieldFilterType filter) {
		this.filter = filter;
	}

}
