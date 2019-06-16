package jenerator.annotations.constraints;

import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

public class StringConstraints extends CommonConstraints {

	private long minLenght;
	private long maxLength;
	private StringSimpleFormat stringSimpleFormat;

	public StringConstraints() {
		super();
		setMinLenght(constraintsConfiguration.getMinLenght());
		setMaxLenght(constraintsConfiguration.getMaxLenght());
		setStringSimpleFormat(StringSimpleFormat.ONLY_LETTERS);
	}

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
		if (maxLenght >= minLenght)
			this.maxLength = maxLenght;
		else
			this.maxLength = minLenght + 5;
	}

	/**
	 * Format of string to generate.
	 * 
	 * @return the format of string that has to be generated.
	 * @see jenerator.engine.generators.StringGenerator.StringSimpleFormat
	 */
	public StringSimpleFormat getStringSimpleFormat() {
		return stringSimpleFormat;
	}

	public void setStringSimpleFormat(StringSimpleFormat stringSimpleFormat) {
		this.stringSimpleFormat = stringSimpleFormat;
	}

}
