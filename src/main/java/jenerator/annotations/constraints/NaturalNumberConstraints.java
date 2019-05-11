package jenerator.annotations.constraints;

public class NaturalNumberConstraints extends CommonConstraints {

	private long minValue;
	private long maxValue;

	public void setMinValue(long minValue) {
		this.minValue = minValue;
	}

	public void setMaxValue(long maxValue) {
		if (maxValue > minValue)
			this.maxValue = maxValue;
		else
			// TODO Logger warning here
			this.maxValue = this.minValue;
	}

	public long getMinValue() {
		return minValue;
	}

	public long getMaxValue() {
		return maxValue;
	}
}
