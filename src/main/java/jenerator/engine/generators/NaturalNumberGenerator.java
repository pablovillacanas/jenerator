package jenerator.engine.generators;

import jenerator.annotations.constraints.CommonConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;

public class NaturalNumberGenerator extends ValueGenerator<Number> {

	@SuppressWarnings("unchecked")
	@Override
	public Number getValue(CommonConstraints constraints) {
		NaturalNumberConstraints naturalNumberConstraints = (NaturalNumberConstraints) constraints;
		return (Number) random.nextLong(naturalNumberConstraints.getMinValue(), naturalNumberConstraints.getMaxValue());
	}

}
