package jenerator.engine.generators;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.StringConstraints;

public class StringGenerator extends ValueGenerator<String> {

	private static StringBuilder sb = new StringBuilder();

	public static enum StringSimpleFormat {
		ALPHANUMERIC, ONLY_DIGITS, ONLY_LETTERS, DIGITS_AND_LETTERS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getValue(Constraints constraints) {
		StringConstraints stringConstraints = (StringConstraints) constraints;
		CharGenerator charGenerator = new CharGenerator();
		long stringLenght = stringConstraints.getMinLenght();
		sb.setLength(0);
		if (stringConstraints.getMinLenght() != stringConstraints.getMaxLenght()) {
			stringLenght = new RandomDataGenerator().nextLong(stringConstraints.getMinLenght(),
					stringConstraints.getMaxLenght());
		}
		for (int i = 0; i < stringLenght; i++) {
			sb.append(charGenerator.getValue(stringConstraints));
		}
		String string = sb.toString();
		return string;
	}
}
