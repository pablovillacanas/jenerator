package jenerator.engine.generators;

import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

public class StringGeneratorTest {

	StringConstraints stringConstraints;
	StringGenerator stringGenerator;
	private final long numGenerations = 50;

	@Before
	public void init() {
		stringConstraints = new StringConstraints();
		stringGenerator = new StringGenerator(numGenerations, stringConstraints);
	}

	@Test
	public void testLetters() {
		stringConstraints.setMinLenght(15);
		stringConstraints.setMaxLenght(20);
		stringConstraints.setUnique(true);
		stringGenerator.generate();
		String value = stringGenerator.getValue();
		for (int i = 0; i < numGenerations; i++) {
			assert (value.matches("[A-Za-z]+"));
		}
	}

	@Test
	public void testNumbers() {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(7);
		stringConstraints.setUnique(false);
		stringConstraints.setStringSimpleFormat(StringSimpleFormat.ONLY_DIGITS);
		stringGenerator.generate();
		String value = stringGenerator.getValue();
		for (int i = 0; i < numGenerations; i++) {
			assert (value.matches("[0-9]+"));
		}
	}
}
