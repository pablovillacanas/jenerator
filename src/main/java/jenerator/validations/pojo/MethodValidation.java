package jenerator.validations.pojo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodValidation {

	public static boolean isGetter(Method method, String fieldname) {
		fieldname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
			if (method.getName().matches("^get" + fieldname + "$") && !method.getReturnType().equals(void.class))
				return true;
			if (method.getName().matches("^is" + fieldname + "$") && method.getReturnType().equals(boolean.class))
				return true;
		}
		return false;
	}

	public static boolean isSetter(Method method, String fieldname) {
		fieldname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
		return Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(void.class)
				&& method.getParameterTypes().length == 1 && method.getName().matches("^set" + fieldname + "$");
	}
}
