package jenerator.engine.generators;

import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

public class StringGeneratorTest {

	StringConstraints stringConstraints;

	@Before
	public void init() {
		stringConstraints = new StringConstraints();
	}

	@Test
	public void testLetters() {
		stringConstraints.setMinLenght(15);
		stringConstraints.setMaxLenght(20);
		String value = new StringGenerator().getValue(stringConstraints);
		for (int i = 0; i < 50; i++) {
			assert (value.matches("[A-Za-z]+"));
		}
	}

	@Test
	public void testNumbers() {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(7);
		stringConstraints.setStringSimpleFormat(StringSimpleFormat.ONLY_DIGITS);
		String value = new StringGenerator().getValue(stringConstraints);
		for (int i = 0; i < 50; i++) {
			assert(value.matches("[0-9]+"));
		}
	}
}
