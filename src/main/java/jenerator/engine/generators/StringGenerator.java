package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;

public class StringGenerator extends ValueGenerator<String> {

	private StringConstraints constraints;

	public StringGenerator(long quantity, StringConstraints constraints) {
		super(quantity, constraints);
		this.constraints = constraints;
	}

	/**
	 * <p>
	 * Enum that contains all the possible agrupations of characters to generate an
	 * array
	 * <p>
	 * 
	 * @author pablo
	 *
	 */
	public static enum StringSimpleFormat {
		ALPHANUMERIC("alpha"), ONLY_DIGITS("digits"), ONLY_LETTERS("letters"), DIGITS_AND_LETTERS("digits_letters");

		List<Character> characters = new ArrayList<Character>();

		private StringSimpleFormat(String string) {
			switch (string) {
			case "alpha":
				getAlphaNumerics();
				break;
			case "digits_letters":
				getDigits();
				getLetters();
				break;
			case "digits":
				getDigits();
				break;
			case "letters":
				getLetters();
				break;
			}
		}

		private void getLetters() {
			// Minus
			for (char c = 97; c < 123; c++) {
				characters.add(c);
			}
			// Mayus
			for (char c = 65; c < 91; c++) {
				characters.add(c);
			}
		}

		private void getDigits() {
			for (char c = 48; c < 58; c++) {
				characters.add(c);
			}
		}

		private void getAlphaNumerics() {
			for (char c = 33; c < 48; c++) {
				characters.add(c);
			}
			for (char c = 58; c < 65; c++) {
				characters.add(c);
			}
		}

		public List<Character> getCharacters() {
			return characters;
		}

		public void setCharacters(List<Character> characters) {
			this.characters = characters;
		}

	}

	@Override
	public Collection<String> generate() throws CoverageExceededException {
		if (constraints.getUnique()) {
			setValueContainer(new HashSet<String>());
			if (calculateCoverage() >= CRITICAL_COVERAGE) {
				loadAllValues();
			} else {
				stringRandomGenerator(getValuesToGenerate());
			}
		} else {
			setValueContainer(new ArrayList<String>());
			stringRandomGenerator(getValuesToGenerate());
		}
		return getValueContainer();
	}

	private void stringRandomGenerator(long valuesToGenerate) {
		StringBuilder stringBuilder = new StringBuilder();
		StringSimpleFormat stringSimpleFormat = constraints.getStringSimpleFormat();
		while (!containerIsFilled()) {
			long stringLenght = random.nextLong(constraints.getMinLenght(), constraints.getMaxLenght());
			for (int i = 0; i < stringLenght; i++) {
				List<Character> characters = stringSimpleFormat.getCharacters();
				int randomIndex = new Random().nextInt(characters.size());
				Character character = characters.get(randomIndex);
				stringBuilder.append(character);
			}
			addValue(stringBuilder.toString());
			stringBuilder.delete(0, (int) stringLenght);
		}
	}

	private void loadAllValues() {
		long minLenght = constraints.getMinLenght();
		long maxLenght = constraints.getMaxLenght();
		long currentSize = 1;
		do {
			for (long i = maxLenght; i > minLenght; i--) {
				// TODO get all variations with repetitions within range.
			}
			currentSize++;
		} while (currentSize <= maxLenght);
	}

	@Override
	protected double calculateCoverage() throws CoverageExceededException {
		double possiblilities = getPossibilities();
		if (possiblilities >= getValuesToGenerate())
			return getValuesToGenerate() / possiblilities;
		else
			throw new CoverageExceededException(getQuantity(), (int) possiblilities);
	}

	/**
	 * <p>
	 * Variations with repetition. Sumatory of all variation with repetitions within
	 * range - both inclusive.
	 * </p>
	 * 
	 * @param minLenght
	 * @param maxLenght
	 * @param sizeGroup
	 * @return
	 */
	protected long getPossibilities() {
		int sizegroup = constraints.getStringSimpleFormat().characters.size();
		long possibilities = 0;
		for (long i = constraints.getMinLenght(); i <= constraints.getMaxLenght(); i++) {
			possibilities = +(long) Math.pow(sizegroup, i);
		}
		return possibilities;
	}
}
