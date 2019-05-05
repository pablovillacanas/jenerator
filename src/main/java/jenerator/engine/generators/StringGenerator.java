package jenerator.engine.generators;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.StringConstraints;

public class StringGenerator extends ValueGenerator<String> {

	StringConstraints stringConstraints;
	CharGenerator charGenerator;
	StringBuilder sb = new StringBuilder();

	public static enum StringSimpleFormat {
		ALPHANUMERIC, ONLY_DIGITS, ONLY_LETTERS, DIGITS_AND_LETTERS;
	}

	public StringGenerator(StringConstraints stringConstraints) {
		this.stringConstraints = stringConstraints;
		charGenerator = new CharGenerator(stringConstraints);
	}

	@Override
	public String getValue() {
		long stringLenght = stringConstraints.getMinLenght();
		sb.setLength(0);
		if (stringConstraints.getMinLenght() != stringConstraints.getMaxLenght()) {
			stringLenght = new RandomDataGenerator().nextLong(stringConstraints.getMinLenght(),
					stringConstraints.getMaxLenght());
		}
		for (int i = 0; i < stringLenght; i++) {
			sb.append(charGenerator.getValue());
		}
		String string = sb.toString();

		return string;
	}
}
