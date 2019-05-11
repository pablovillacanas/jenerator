package jenerator.annotations.constraints;

import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

public class StringConstraints extends CommonConstraints {

	private long minLenght = 5;
	private long maxLength = 10;
	private StringSimpleFormat stringSimpleFormat = StringSimpleFormat.ONLY_LETTERS;

	public long getMinLenght() {
		return minLenght;
	}

	public void setMinLenght(long minLenght) {
		this.minLenght = minLenght;
	}

	public long getMaxLenght() {
		return maxLength;
	}

	public void setMaxLenght(long maxLenght) {
		if (maxLenght > minLenght)
			this.maxLength = maxLenght;
		else
			this.maxLength = minLenght + 5;
	}

	public StringSimpleFormat getStringSimpleFormat() {
		return stringSimpleFormat;
	}

	public void setStringSimpleFormat(StringSimpleFormat stringSimpleFormat) {
		this.stringSimpleFormat = stringSimpleFormat;
	}

}
