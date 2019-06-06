package jenerator.engine.generators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.StringGenerator.StringSimpleFormat;
import jenerator.engine.parser.ElementFromSourceException;

public class StringGeneratorTest {

	private StringConstraints stringConstraints;
	private StringGenerator stringGenerator;
	private long numGenerations = 50;

	@Before
	public void setUp() throws Exception {
		stringConstraints = new StringConstraints();
		stringGenerator = new StringGenerator(numGenerations, stringConstraints);
		stringConstraints.setSource("");
	}

	@Test
	public void testCalculateCoverage() throws CoverageExceededException {
		stringConstraints.setMinLenght(1);
		stringConstraints.setMaxLenght(1);
		stringConstraints.setUnique(false);
		stringConstraints.setStringSimpleFormat(StringSimpleFormat.ONLY_DIGITS);

		stringConstraints.setNullable(0.0);
		stringGenerator.setQuantity(10);
		assertEquals(stringGenerator.calculateCoverage(), 1.0, 0);

		stringConstraints.setNullable(0.5);
		stringGenerator.setQuantity(10);
		assertEquals(stringGenerator.calculateCoverage(), 0.5, 0);
	}

	@Test(expected = CoverageExceededException.class)
	public void testCalculateCoverageExceeded() throws CoverageExceededException {
		stringConstraints.setMinLenght(1);
		stringConstraints.setMaxLenght(1);
		stringConstraints.setUnique(true);
		stringConstraints.setStringSimpleFormat(StringSimpleFormat.ONLY_DIGITS);

		stringConstraints.setNullable(0.0);
		stringGenerator.setQuantity(11);
		assertEquals(1.1, stringGenerator.calculateCoverage(), 0);
	}

	@Test
	public void testStringGeneratorNumbers() throws CoverageExceededException, ElementFromSourceException {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(7);
		stringConstraints.setUnique(false);
		stringConstraints.setNullable(0.0);
		stringConstraints.setStringSimpleFormat(StringSimpleFormat.ONLY_DIGITS);
		stringGenerator.generate();
		for (int i = 0; i < numGenerations; i++) {
			String value = stringGenerator.getValue();
			assert (value.matches("[0-9]+"));
			assert (value.length() >= 5 && value.length() <= 7);
		}
		assert (stringGenerator.getValueContainer().isEmpty());
	}

	@Test
	public void testStringGeneratorString() throws CoverageExceededException, ElementFromSourceException {
		stringConstraints.setMinLenght(15);
		stringConstraints.setMaxLenght(20);
		stringConstraints.setUnique(true);
		stringConstraints.setNullable(0.0);
		stringGenerator.generate();
		for (int i = 0; i < numGenerations; i++) {
			String value = stringGenerator.getValue();
			assert (value.matches("[A-Za-z]+"));
			assert (value.length() >= 15 && value.length() <= 20);
		}
		assert (stringGenerator.getValueContainer().isEmpty());
	}
}
