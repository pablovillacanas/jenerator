package jenerator;

import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

import jenerator.annotations.constraints.DecimalNumberConstraints;

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
		System.out.println(Experiment.class.getDeclaredField("i").getType().getCanonicalName());
		System.out.println(String.class.getCanonicalName());
//		assertTrue(Experiment.class.getDeclaredField("i").getType().getCanonicalName().equals(String.class.getCanonicalName()));
		assertTrue(int.class.isAssignableFrom(Experiment.class.getDeclaredField("i").getType()));
	}

	@Test
	public void cosas() throws NoSuchFieldException, SecurityException {
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
			System.out.println(finalResult);
		}
	}
}
