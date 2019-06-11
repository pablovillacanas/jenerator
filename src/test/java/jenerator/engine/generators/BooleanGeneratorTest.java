package jenerator.engine.generators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.BooleanConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class BooleanGeneratorTest {

	private BooleanConstraints booleanConstraints;
	private final long numGenerations = 100;

	@Before
	public void init() {
		booleanConstraints = new BooleanConstraints();
	}

	@Test
	public void testConsraintsTrueFalseExactly()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		booleanConstraints.setRelationTrueFalse(0.75d);
		ValueGenerator<Boolean> bg = new BooleanGenerator(numGenerations, booleanConstraints);
		Collection<Boolean> generate = bg.generate();
		Iterator<Boolean> iterator = generate.iterator();
		assertTrue(bg.getValueContainer().size() == numGenerations);
		int numf = 0, numt = 0;
		for (int i = 0; i < numGenerations; i++) {
			Boolean value = iterator.next();
			if (value) {
				numt++;
			} else {
				numf++;
			}
		}
		assertTrue(numt == 75);
		assertTrue(numf == 25);
	}

	@Test
	public void testConsraintsTrueFalseRandom()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		booleanConstraints.setRelationTrueFalse(-1);
		ValueGenerator<Boolean> bg = new BooleanGenerator(numGenerations, booleanConstraints);
		Collection<Boolean> generate = bg.generate();
		Iterator<Boolean> iterator = generate.iterator();
		assertTrue(bg.getValueContainer().size() == numGenerations);
		int numf = 0, numt = 0;
		for (int i = 0; i < numGenerations; i++) {
			Boolean value = iterator.next();
			if (value) {
				numt++;
			} else {
				numf++;
			}
		}
		System.out.println("falses: " + numf + "trues: " + numt);
	}

	@Test
	public void testConsraintsTrueFalseDefault()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		ValueGenerator<Boolean> bg = new BooleanGenerator(numGenerations, booleanConstraints);
		Collection<Boolean> generate = bg.generate();
		Iterator<Boolean> iterator = generate.iterator();
		assertTrue(bg.getValueContainer().size() == numGenerations);
		int numf = 0, numt = 0;
		for (int i = 0; i < numGenerations; i++) {
			Boolean value = iterator.next();
			if (value) {
				numt++;
			} else {
				numf++;
			}
		}
		assertEquals(50, numt, 0);
		assertEquals(50, numf, 0);
	}

	@Test
	public void testConsraintsTrueFalseDefaultAndNullable()
			throws NumberIsTooLargeException, CoverageExceededException, ElementFromSourceException {
		booleanConstraints.setNullable(0.5);
		ValueGenerator<Boolean> bg = new BooleanGenerator(numGenerations, booleanConstraints);
		Collection<Boolean> generate = bg.generate();
		Iterator<Boolean> iterator = generate.iterator();
		assertTrue(bg.getValueContainer().size() == numGenerations);
		int numf = 0, numt = 0, numn = 0;
		for (int i = 0; i < numGenerations; i++) {
			Boolean value = iterator.next();
			if (value == null) {
				numn++;
			} else if (value == false) {
				numf++;
			} else {
				numt++;
			}
		}
		assertEquals(25, numt, 0);
		assertEquals(25, numf, 0);
		assertEquals(50, numn, 0);
	}
}
