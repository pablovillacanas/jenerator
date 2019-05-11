package jenerator;

import org.junit.Test;

public class Experiment {

	@Test
	public void test() {
		Long l = new Long(3);
//		boolean assignableFrom = l.getClass().isAssignableFrom(Number.class);
		
		assert(Number.class.isAssignableFrom(l.getClass()));
	}
}
