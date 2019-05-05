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
		StringGenerator stringGenerator = new StringGenerator(stringConstraints);
		for (int i = 0; i < 50; i++) {
			String value = stringGenerator.getValue();
			assert (value.matches("[A-Za-z]+"));
		}
	}

	@Test
	public void testNumbers() {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(7);
		stringConstraints.setStringSimpleFormat(StringSimpleFormat.ONLY_DIGITS);
		StringGenerator stringGenerator = new StringGenerator(stringConstraints);
		for (int i = 0; i < 50; i++) {
			String value = stringGenerator.getValue();
			assert(value.matches("[0-9]+"));
		}
	}
}
