package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class StringGenerator extends ValueGenerator<String> {

	private StringConstraints constraints;

	public StringGenerator(long quantity, StringConstraints constraints) {
		super(quantity, constraints);
		this.constraints = constraints;
		if (this.constraints == null) {
			this.constraints = new StringConstraints();
		}
	}

	/**
	 * <p>
	 * Enum that contains all the possible agrupations of characters to generate an
	 * array
	 * <p>
	 * <ul>
	 * <li>{@link jenerator.engine.generators.SringSimpleFormat#ALPHANUMERIC
	 * ALPHANUMERIC}</li>
	 * <li>{@link jenerator.engine.generators.SringSimpleFormat#ONLY_DIGITS
	 * ONLY_DIGITS}</li>
	 * <li>{@link jenerator.engine.generators.SringSimpleFormat#ONLY_LETTERS
	 * ONLY_LETTERS}</li>
	 * <li>{@link jenerator.engine.generators.SringSimpleFormat#DIGITS_AND_LETTERS
	 * DIGITS_AND_LETTERS}</li>
	 * </ul>
	 * 
	 * @author Pablo Villacanas
	 *
	 */
	public static enum StringSimpleFormat {
		/**
		 * Contains ASCII characters from 33 to 127.
		 */
		ALPHANUMERIC("alpha"), ONLY_DIGITS("digits"), ONLY_LETTERS("letters"), DIGITS_AND_LETTERS("digits_letters");

		private List<Character> characters = new ArrayList<Character>();
		private Pattern pattern;

		private StringSimpleFormat(String string) {
			switch (string) {
			case "alpha":
				getAlphaNumerics();
				pattern = Pattern.compile(".*");
				break;
			case "digits_letters":
				getDigits();
				getLetters();
				pattern = Pattern.compile("[a-zA-Z0-9]+");
				break;
			case "digits":
				getDigits();
				pattern = Pattern.compile("[0-9]+");
				break;
			case "letters":
				getLetters();
				pattern = Pattern.compile("[a-zA-Z]+");
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
			for (char c = 33; c < 127; c++) {
				characters.add(c);
			}
		}

		public List<Character> getCharacters() {
			return characters;
		}

		public Pattern getPattern() {
			return pattern;
		}

	}

	@Override
	public void valuesRandomGenerator() {
		StringBuilder stringBuilder = new StringBuilder();
		StringSimpleFormat stringSimpleFormat = constraints.getStringSimpleFormat();
		while (!containerIsFilled()) {
			long stringLenght = -1;
			if (constraints.getMinLenght() == constraints.getMaxLenght()) {
				stringLenght = constraints.getMinLenght();
			} else {
				stringLenght = random.nextLong(constraints.getMinLenght(), constraints.getMaxLenght());
			}
			for (int i = 0; i < stringLenght; i++) {
				List<Character> characters = stringSimpleFormat.getCharacters();
				int randomIndex = new Random().nextInt(characters.size());
				Character character = characters.get(randomIndex);
				stringBuilder.append(character);
			}
			getValueContainer().add(stringBuilder.toString());
			stringBuilder.delete(0, (int) stringLenght);
		}
	}

	@Override
	public List<String> loadAllValues() {
		long minLenght = constraints.getMinLenght();
		long maxLenght = constraints.getMaxLenght();
		while (!containerIsFilled()) {
			for (long i = maxLenght; i > minLenght; i--) {
				// TODO get all variations with repetitions within range.
			}
		}
		return null;
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
	@Override
	protected long getPossibilities() {
		int sizegroup = constraints.getStringSimpleFormat().characters.size();
		long possibilities = 0;
		for (long i = constraints.getMinLenght(); i <= constraints.getMaxLenght(); i++) {
			possibilities = +(long) Math.pow(sizegroup, i);
		}
		return possibilities;
	}

	@Override
	public Collection<String> generateFromSource() throws ElementFromSourceException, CoverageExceededException {
		initSourceReader();
		Stream<String> stream = Streams.stream(sourceReader.iterator());
		Collection<String> collectedPossibilities = stream.filter(string -> {
			if (string.length() <= constraints.getMaxLenght() && string.length() >= constraints.getMinLenght()
					&& constraints.getStringSimpleFormat().getPattern().matcher(string).matches()
					&& string.length() != 0)
				return true;
			else
				return false;
		}).collect(Collectors.toList());
		if (!constraints.getUnique()) {
			setValueContainer(collectedPossibilities);
		} else {
			collectedPossibilities = collectedPossibilities.stream().collect(Collectors.toSet());
			if (collectedPossibilities.size() < getValuesToGenerate()) {
				throw new CoverageExceededException(getQuantity(), collectedPossibilities.size());
			}
		}
		return collectedPossibilities;
	}
}
