package jenerator.engine.generators;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class DecimalNumberGeneratorTest {

	DecimalNumberConstraints numberConstraints;
	private final long numGenerations = 500;

	@Before
	public void init() {
		numberConstraints = new DecimalNumberConstraints();
		numberConstraints.setSource("");
	}

	@Test
	public void testNumbers() throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(7);
		numberConstraints.setUnique(false);
		numberConstraints.setNullable(0.0);
		numberConstraints.setPrecision((short) 1);
		DecimalNumberGenerator<Number> nng = new DecimalNumberGenerator<Number>(Double.class, numGenerations,
				numberConstraints);
		Collection<Number> generate = nng.generate();
		Iterator<Number> iterator = generate.iterator();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assert (String.valueOf(value).matches("[0-9]+.[0-9]{1}"));
		}
	}

	@Test
	public void testNumbersUniqueNonCritical()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(700);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		numberConstraints.setPrecision((short) 1);
		DecimalNumberGenerator<Number> nng = new DecimalNumberGenerator<Number>(Float.class, numGenerations,
				numberConstraints);
		Collection<Number> generate = nng.generate();
		Iterator<Number> iterator = generate.iterator();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assert (String.valueOf(value).matches("[0-9]+.[0-9]{1}"));
		}
	}

	@Test
	public void testNumbersCritical()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(55);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		numberConstraints.setPrecision((short) 1);
		DecimalNumberGenerator<Number> nng = new DecimalNumberGenerator<Number>(Double.class, numGenerations,
				numberConstraints);
		Collection<Number> generate = nng.generate();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		Iterator<Number> iterator = generate.iterator();
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assert (String.valueOf(value).matches("[0-9]+.[0-9]{1}"));
		}
	}

	@Test(expected = CoverageExceededException.class)
	public void testNumbersOverCritical()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(54);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		numberConstraints.setPrecision((short) 1);
		DecimalNumberGenerator<Number> nng = new DecimalNumberGenerator<Number>(Float.class, numGenerations,
				numberConstraints);
		nng.generate();
	}
}
