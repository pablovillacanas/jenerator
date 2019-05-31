package jenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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

	@SuppressWarnings("unused")
	@Test
	public void cosas() throws NoSuchFieldException, SecurityException {
		ArrayList<Class<?>> clases = new ArrayList<Class<?>>();
		clases.add(Short.class);
		clases.add(Byte.class);
		clases.add(Integer.class);
		for (Class<?> class1 : clases) {
			boolean assignableFrom = getShortClass().isAssignableFrom(class1);
			System.out.println(assignableFrom);
		}
	}

	public Class<?> getShortClass() {
		return Short.class;
	}
}
