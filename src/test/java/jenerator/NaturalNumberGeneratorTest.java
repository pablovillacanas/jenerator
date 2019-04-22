package jenerator;

import org.junit.Test;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.reader.NaturalNumberGeneratorReader;
import jenerator.engine.generators.NaturalNumberGenerator;

public class NaturalNumberGeneratorTest {

	@NaturalNumberGenerable
	private Integer i;

	@Test
	public void test() throws NoSuchFieldException, SecurityException {
		NaturalNumberGenerable annotation = NaturalNumberGeneratorTest.class.getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberGeneratorReader reader = new NaturalNumberGeneratorReader(annotation);

		NaturalNumberGenerator<Long> naturalNumberGenerator2 = new NaturalNumberGenerator<Long>(reader) {
		};
		assert (naturalNumberGenerator2.getRandomValue() instanceof Long);

		NaturalNumberGenerator<Integer> naturalNumberGenerator = new NaturalNumberGenerator<Integer>(reader) {
		};
		assert (naturalNumberGenerator.getRandomValue() instanceof Integer);

		NaturalNumberGenerator<Short> naturalNumberGenerator3 = new NaturalNumberGenerator<Short>(reader) {
		};
		assert (naturalNumberGenerator3.getRandomValue() instanceof Short);

		NaturalNumberGenerator<Byte> naturalNumberGenerator4 = new NaturalNumberGenerator<Byte>(reader) {
		};
		assert (naturalNumberGenerator4.getRandomValue() instanceof Byte);
	}
}
