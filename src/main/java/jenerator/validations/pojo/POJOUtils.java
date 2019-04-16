package jenerator.validations.pojo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class POJOUtils {

	public static <T extends Object> ArrayList<Field> getDeclaredFields(Class<T> class1) {
		return (ArrayList<Field>) Arrays.asList(class1.getDeclaredFields()).stream()
				.filter(field -> !field.getName().startsWith("this$")).collect(Collectors.toList());
	}
}
