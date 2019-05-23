package jenerator;

import static org.junit.Assert.assertTrue;

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
		System.out.println(Experiment.class.getDeclaredField("i").getType().getCanonicalName());
		System.out.println(String.class.getCanonicalName());
//		assertTrue(Experiment.class.getDeclaredField("i").getType().getCanonicalName().equals(String.class.getCanonicalName()));
		assertTrue(int.class.isAssignableFrom(Experiment.class.getDeclaredField("i").getType()));
	}
}
