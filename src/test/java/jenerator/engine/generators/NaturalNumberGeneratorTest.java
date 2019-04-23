package jenerator.engine.generators;

import org.junit.Test;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.reader.NaturalNumberGeneratorReader;
import jenerator.engine.generators.ByteGenerator;
import jenerator.engine.generators.IntegerGenerator;
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.ShortGenerator;

public class NaturalNumberGeneratorTest {

	@NaturalNumberGenerable
	private Integer i;

	@Test
	public void test() throws NoSuchFieldException, SecurityException {
		NaturalNumberGenerable annotation = NaturalNumberGeneratorTest.class.getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberGeneratorReader reader = new NaturalNumberGeneratorReader(annotation);

		NaturalNumberGenerator<Integer> naturalNumberGenerator = new IntegerGenerator(reader);
		assert (naturalNumberGenerator.getRandomValue() instanceof Integer);

		NaturalNumberGenerator<Long> naturalNumberGenerator2 = new LongGenerator(reader);
		assert (naturalNumberGenerator2.getRandomValue() instanceof Long);

		NaturalNumberGenerator<Short> naturalNumberGenerator3 = new ShortGenerator(reader);
		assert (naturalNumberGenerator3.getRandomValue() instanceof Short);

		NaturalNumberGenerator<Byte> naturalNumberGenerator4 = new ByteGenerator(reader);
		assert (naturalNumberGenerator4.getRandomValue() instanceof Byte);
	}
}
