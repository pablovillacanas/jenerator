package jenerator.engine.generators;

import org.junit.Test;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.readers.NaturalNumberConstraintsReader;
import jenerator.annotations.readers.exceptions.MinMaxConstraintException;
import jenerator.engine.generators.ByteGenerator;
import jenerator.engine.generators.IntegerGenerator;
import jenerator.engine.generators.LongGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.ShortGenerator;

public class NaturalNumberGeneratorTest {

	@NaturalNumberGenerable
	private Integer i;

	@Test
	public void testConcordanceBetweenFieldTypeAndValueGenerated() throws NoSuchFieldException, SecurityException, MinMaxConstraintException {
		NaturalNumberGenerable annotation = NaturalNumberGeneratorTest.class.getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberConstraints constraints = new NaturalNumberConstraintsReader().readValues(annotation);

		NaturalNumberGenerator<Integer> naturalNumberGenerator = new IntegerGenerator(constraints);
		assert (naturalNumberGenerator.getValue() instanceof Integer);

		NaturalNumberGenerator<Long> naturalNumberGenerator2 = new LongGenerator(constraints);
		assert (naturalNumberGenerator2.getValue() instanceof Long);

		NaturalNumberGenerator<Short> naturalNumberGenerator3 = new ShortGenerator(constraints);
		assert (naturalNumberGenerator3.getValue() instanceof Short);

		NaturalNumberGenerator<Byte> naturalNumberGenerator4 = new ByteGenerator(constraints);
		assert (naturalNumberGenerator4.getValue() instanceof Byte);
	}
}
