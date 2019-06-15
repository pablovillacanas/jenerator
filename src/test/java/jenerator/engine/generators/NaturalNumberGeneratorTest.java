package jenerator.engine.generators;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class NaturalNumberGeneratorTest {

	NaturalNumberConstraints numberConstraints;
	private final long numGenerations = 50;

	@Before
	public void init() {
		numberConstraints = new NaturalNumberConstraints();
		numberConstraints.setSource("");
	}

	@Test
	public void testNoConsraints()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		NaturalNumberGenerator<Number> nng = new NaturalNumberGenerator<Number>(Short.class, numGenerations, null);
		Collection<Number> generate = nng.generate();
		Iterator<Number> iterator = generate.iterator();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assertTrue(String.valueOf(value).matches("-?[0-9]+"));
		}
	}

	@Test
	public void testNumbers() throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(7);
		numberConstraints.setUnique(false);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator<Number> nng = new NaturalNumberGenerator<Number>(Short.class, numGenerations,
				numberConstraints);
		Collection<Number> generate = nng.generate();
		Iterator<Number> iterator = generate.iterator();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assert (String.valueOf(value).matches("-?[0-9]+"));
		}
	}

	@Test
	public void testNumbersUniqueNonCritical()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(700);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator<Number> nng = new NaturalNumberGenerator<Number>(Integer.class, numGenerations,
				numberConstraints);
		Collection<Number> generate = nng.generate();
		Iterator<Number> iterator = generate.iterator();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assert (String.valueOf(value).matches("-?[0-9]+"));
		}
	}

	@Test
	public void testNumbersCritical()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(55);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator<Number> nng = new NaturalNumberGenerator<Number>(Byte.class, numGenerations,
				numberConstraints);
		Collection<Number> generate = nng.generate();
		Iterator<Number> iterator = generate.iterator();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = iterator.next();
			assert (String.valueOf(value).matches("-?[0-9]+"));
		}
	}

	@Test(expected = CoverageExceededException.class)
	public void testNumbersOverCritical()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(54);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator<Number> nng = new NaturalNumberGenerator<Number>(Long.class, numGenerations,
				numberConstraints);
		nng.generate();
	}

	@Test(expected = CoverageExceededException.class)
	public void testNumbersOverCriticalInSource()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		numberConstraints.setMinValue(-2);
		numberConstraints.setMaxValue(0);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		numberConstraints.setSource("numbers.txt");
		NaturalNumberGenerator<Number> nng = new NaturalNumberGenerator<Number>(Integer.class, numGenerations,
				numberConstraints);
		nng.generate();
	}
}
