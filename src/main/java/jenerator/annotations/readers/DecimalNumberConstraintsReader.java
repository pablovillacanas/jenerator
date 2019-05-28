package jenerator.annotations.readers;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.constraints.DecimalNumberConstraints;

public class DecimalNumberConstraintsReader<A extends DecimalNumberGenerable> extends CommonConstraintsReader<A> {

	DecimalNumberConstraints decimalNumberConstraints;

	public DecimalNumberConstraintsReader() {
		this.decimalNumberConstraints = new DecimalNumberConstraints();
	}

	public DecimalNumberConstraints readValues(DecimalNumberGenerable annotation) {
		decimalNumberConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		decimalNumberConstraints.setMinValue(annotation.minValue());
		decimalNumberConstraints.setMaxValue(annotation.maxValue());
		decimalNumberConstraints.setPrecision(annotation.precision());
		return decimalNumberConstraints;
	}
}
