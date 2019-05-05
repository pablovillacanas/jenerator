package jenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

//TODO debemos hacer el tema de los Strings ahora que podemos
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

	public static <T extends Object> List<T> generateEmpty(Class<T> class1, int numInstances) {
		List<T> instances = new ArrayList<T>();
		try {
			instances.add(class1.getConstructor().newInstance());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return instances;
	}

}
