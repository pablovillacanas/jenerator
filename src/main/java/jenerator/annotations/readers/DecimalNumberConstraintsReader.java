package jenerator.annotations.readers;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.constraints.DecimalNumberConstraints;

public class DecimalNumberConstraintsReader extends ConstraintsReader<DecimalNumberGenerable> {

	DecimalNumberConstraints decimalNumberConstraints;

	public DecimalNumberConstraintsReader() {
		this.decimalNumberConstraints = new DecimalNumberConstraints();
	}

	@Override
	public DecimalNumberConstraints readValues(DecimalNumberGenerable annotation) {
		decimalNumberConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		decimalNumberConstraints.setMinValue(annotation.minValue());
		decimalNumberConstraints.setMaxValue(annotation.maxValue());
		decimalNumberConstraints.setPrecision(annotation.precision());
		return decimalNumberConstraints;
	}
}
