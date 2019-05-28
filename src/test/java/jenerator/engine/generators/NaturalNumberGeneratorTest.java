package jenerator.engine.generators;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.NaturalNumberConstraints;

public class NaturalNumberGeneratorTest {

	NaturalNumberConstraints numberConstraints;
	private final long numGenerations = 50;

	@Before
	public void init() {
		numberConstraints = new NaturalNumberConstraints();
	}

	@Test
	public void testNumbers() {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(7);
		numberConstraints.setUnique(false);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator nng = new NaturalNumberGenerator(numGenerations, numberConstraints);
		nng.generate();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = nng.getValue();
			System.out.print(value + " ");
			assert (String.valueOf(value).matches("[0-9]+"));
		}
		assertTrue(nng.getValueContainer().size() == 0);
	}

	@Test
	public void testNumbersUniqueNonCritical() {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(700);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator nng = new NaturalNumberGenerator(numGenerations, numberConstraints);
		nng.generate();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = nng.getValue();
			System.out.print(value + " ");
			assert (String.valueOf(value).matches("[0-9]+"));
		}
		assertTrue(nng.getValueContainer().size() == 0);
		System.out.println();
	}

	@Test
	public void testNumbersCritical() {
		numberConstraints.setMinValue(5);
		numberConstraints.setMaxValue(55);
		numberConstraints.setUnique(true);
		numberConstraints.setNullable(0.0);
		NaturalNumberGenerator nng = new NaturalNumberGenerator(numGenerations, numberConstraints);
		nng.generate();
		assertTrue(nng.getValueContainer().size() == numGenerations);
		for (int i = 0; i < numGenerations; i++) {
			Number value = nng.getValue();
			System.out.print(value + " ");
			assert (String.valueOf(value).matches("[0-9]+"));
		}
		assertTrue(nng.getValueContainer().size() == 0);
		System.out.println();
	}
}
