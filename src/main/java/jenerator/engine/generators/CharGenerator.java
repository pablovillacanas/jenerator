package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Random;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.StringConstraints;

public class CharGenerator extends ValueGenerator<Character> {

	/**
	 * List of available characters due configuration of generation.
	 */
	private ArrayList<Character> characters = new ArrayList<Character>();

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

	@SuppressWarnings("unchecked")
	@Override
	public Character getValue(Constraints constraints) {
		StringConstraints stringConstraints = (StringConstraints) constraints;
		switch (stringConstraints.getStringSimpleFormat()) {
		case ALPHANUMERIC:
			getAlphaNumerics();
			break;
		case DIGITS_AND_LETTERS:
			getDigits();
			getLetters();
			break;
		case ONLY_DIGITS:
			getDigits();
			break;
		case ONLY_LETTERS:
			getLetters();
			break;
		}
		int randomIndex = new Random().nextInt(characters.size());
		return characters.get(randomIndex);
	}

}
