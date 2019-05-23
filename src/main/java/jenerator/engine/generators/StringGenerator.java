package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.StringConstraints;

public class StringGenerator extends ValueGenerator<String> {

	private StringSimpleFormat stringSimpleFormat;
	private StringConstraints constraints;

	public StringGenerator(StringConstraints constraints) {
		this.constraints = constraints;
	}

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
	public Collection<String> generate(long quantity) {
		if (constraints.getUnique()) {
			setValueContainer(new HashSet<String>());
		} else {
			setValueContainer(new ArrayList<String>());
			StringBuilder sb = new StringBuilder();
			stringSimpleFormat = constraints.getStringSimpleFormat();
			long stringLenght = constraints.getMinLenght();
			if (constraints.getMinLenght() != constraints.getMaxLenght()) {
				stringLenght = new RandomDataGenerator().nextLong(constraints.getMinLenght(),
						constraints.getMaxLenght());
			}
			for (int i = 0; i < stringLenght; i++) {
				List<Character> characters = stringSimpleFormat.getCharacters();
				int randomIndex = new Random().nextInt(characters.size());
				Character character = characters.get(randomIndex);
				sb.append(character);
			}
			getValueContainer().add(sb.toString());
		}
		return getValueContainer();
	}
}
