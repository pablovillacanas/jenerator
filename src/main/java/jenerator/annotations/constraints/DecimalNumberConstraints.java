package jenerator.annotations.constraints;

public class DecimalNumberConstraints extends CommonConstraints {

	private double minValue;
	private double maxValue;
	private short precision;

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public void setMaxValue(double maxValue) {
		if (maxValue > minValue)
			this.maxValue = maxValue;
		else
			// TODO Logger warning here
			this.maxValue = this.minValue;
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public short getPrecision() {
		return precision;
	}

	public void setPrecision(short precision) {
		this.precision = precision;
	}
}
