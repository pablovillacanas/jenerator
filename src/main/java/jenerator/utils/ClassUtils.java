package jenerator.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jenerator.JeneratorConfiguration;
import jenerator.JeneratorConfiguration.FieldFilterType;
import jenerator.filters.GenerableFieldsFilter;

public class ClassUtils {

	public static List<Field> getGenerableFields(Class<?> class1) {
		FieldFilterType fieldTypeFilter = JeneratorConfiguration.getInstance().getFieldFilter();
		return Arrays.asList(class1.getDeclaredFields()).stream().filter(new GenerableFieldsFilter(fieldTypeFilter))
				.collect(Collectors.toList());
	}
}
