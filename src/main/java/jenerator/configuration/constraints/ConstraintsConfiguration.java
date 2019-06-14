package jenerator.configuration.constraints;

/**
 * This class provides the possibility of constraint the values on that fields
 * without values specified. ALl the constrints will take on first instance this
 * values and if there are some annotation, it will take preference for its
 * default values.
 */
public class ConstraintsConfiguration {

	private Long maxNaturalValue = Long.MAX_VALUE;
	private Long minNaturalValue = Long.MIN_VALUE;
	private Integer minLenght = 5;
	private Integer maxLenght = 10;
	private Double maxDecimalValue = Double.MAX_VALUE;
	private Double minDecimalValue = Double.MIN_VALUE;
	private Short decimalPrecision = 2;

	public long getMaxNaturalValue() {
		return maxNaturalValue;
	}

	public ConstraintsConfiguration setMaxNaturalValue(long maxNaturalValue) {
		this.maxNaturalValue = maxNaturalValue;
		return this;
	}

	public ConstraintsConfiguration setMinNaturalValue(long minNaturalValue) {
		this.minNaturalValue = minNaturalValue;
		return this;
	}

	public int getMinLenght() {
		return minLenght;
	}

	public ConstraintsConfiguration setMinLenght(int minLenght) {
		this.minLenght = minLenght;
		return this;
	}

	public int getMaxLenght() {
		return maxLenght;
	}

	public ConstraintsConfiguration setMaxLenght(int maxLenght) {
		this.maxLenght = maxLenght;
		return this;
	}

	public double getMaxDecimalValue() {
		return maxDecimalValue;
	}

	public ConstraintsConfiguration setMaxDecimalValue(double maxDecimalValue) {
		this.maxDecimalValue = maxDecimalValue;
		return this;
	}

	public double getMinDecimalValue() {
		return minDecimalValue;
	}

	public ConstraintsConfiguration setMinDecimalValue(double minDecimalValue) {
		this.minDecimalValue = minDecimalValue;
		return this;
	}

	public short getDecimalPrecision() {
		return decimalPrecision;
	}

	public ConstraintsConfiguration setDecimalPrecision(short decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
		return this;
	}

	/**
	 * @return the minNaturalValue
	 */
	public Long getMinNaturalValue() {
		return minNaturalValue;
	}

	/**
	 * @param minNaturalValue the minNaturalValue to set
	 */
	public void setMinNaturalValue(Long minNaturalValue) {
		this.minNaturalValue = minNaturalValue;
	}
}
