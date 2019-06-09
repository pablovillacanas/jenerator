package jenerator.annotations.constraints;

public class NaturalNumberConstraints extends CommonConstraints {

	private long minValue = Long.MIN_VALUE;
	private long maxValue = Long.MAX_VALUE;

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
