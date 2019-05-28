package jenerator.validations.checkers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.engine.generators.StringGenerator.StringSimpleFormat;
import jenerator.validations.congruence.checkers.UniqueCongruenceChecker;

public class CheckerUniqueTest {

	@NaturalNumberGenerable(minValue = 0, maxValue = 10)
	public Integer integer;

	@StringGenerable(minLenght = 4, maxLenght = 7, style = StringSimpleFormat.ONLY_DIGITS)
	public String string;

	@NaturalNumberGenerable(minValue = 0, maxValue = 10, constraints = @GenerationConstraints(nullable = 0.5))
	public Integer integernull;

	@StringGenerable(minLenght = 1, maxLenght = 3, style = StringSimpleFormat.ONLY_DIGITS, constraints = @GenerationConstraints(nullable = 0.5))
	public String stringnull;

	@Test
	public void testGenerationImpossible() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("integer");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 11);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertFalse(test);
	}

	@Test
	public void testGenerationPossible() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("integer");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 9);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationExact() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("integer");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 10);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationImposibleString() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("string");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 1000);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertFalse(test);
	}

	@Test
	public void testGenerationPossibleString() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("string");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 998);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationExactString() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("string");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 999);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationImpossibleNull() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("integernull");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 21);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertFalse(test);
	}

	@Test
	public void testGenerationPossibleNull() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("integernull");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 19);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationExactNull() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("integernull");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 20);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationImposibleStringNull() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("stringnull");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 1999);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertFalse(test);
	}

	@Test
	public void testGenerationPossibleStringNull() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("stringnull");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 1997);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}

	@Test
	public void testGenerationExactStringNull() throws NoSuchFieldException, SecurityException {
		Field declaredField = CheckerUniqueTest.class.getDeclaredField("stringnull");
		UniqueCongruenceChecker uniqueCongruenceChecker = new UniqueCongruenceChecker((long) 1998);
		boolean test = uniqueCongruenceChecker.test(declaredField);
		assertTrue(test);
	}
}
