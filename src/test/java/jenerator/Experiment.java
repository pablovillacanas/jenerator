package jenerator;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

public class Experiment {

	public int i;

	@Test
	public void test() throws NoSuchFieldException, SecurityException {
//		Long l = new Long(3);
//		boolean assignableFrom = l.getClass().isAssignableFrom(Number.class);
//		Field field = getClass().getDeclaredField("i");
//		assert (Integer.class.isAssignableFrom(FieldUtils.getFieldClass(field)));
	}

	@Test
	public void combinatoryTest() throws NoSuchFieldException, SecurityException {
//		assertTrue(Experiment.class.getDeclaredField("i").getType().getCanonicalName().equals(String.class.getCanonicalName()));
		assertTrue(int.class.isAssignableFrom(Experiment.class.getDeclaredField("i").getType()));
		assertTrue(String.valueOf("31123").matches("[0-9]+"));
	}

	@Test
	public void cosas2() throws NoSuchFieldException, SecurityException {
		double minValue = 50.5;
		double maxValue = 55.5;
		short precision = 2;
		double newminValue = minValue * Math.pow(10, precision);
		double newmaxValue = maxValue * Math.pow(10, precision);
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		for (int i = 0; i < 10000; i++) {
			long nextLong = randomDataGenerator.nextLong((long) newminValue, (long) newmaxValue);
			double finalResult = nextLong / Math.pow(10, precision);
			assertTrue(finalResult <= maxValue && finalResult >= minValue);
		}
	}

	List<String> stringList = new ArrayList<String>();
	List<ArrayList<String>> listList = new ArrayList<ArrayList<String>>();

	@Test
	public void cosas5() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field stringListField = getClass().getDeclaredField("stringList");
		ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
		Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
		System.out.println(stringListClass); // class java.lang.String.

		Field integerListField = getClass().getDeclaredField("listList");
		ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
		Type type = integerListType.getActualTypeArguments()[0];
		String typeName = type.getTypeName();
		System.out.println(typeName);
	}

}
