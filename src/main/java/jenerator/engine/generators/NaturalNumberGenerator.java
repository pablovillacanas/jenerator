package jenerator.engine.generators;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;

public class NaturalNumberGenerator extends ValueGenerator<Number> {

	@SuppressWarnings("unchecked")
	@Override
	public Number getValue(Constraints constraints) {
		NaturalNumberConstraints naturalNumberConstraints = (NaturalNumberConstraints) constraints;
		return (Number) random.nextLong(naturalNumberConstraints.getMinValue(), naturalNumberConstraints.getMaxValue());
	}

}
