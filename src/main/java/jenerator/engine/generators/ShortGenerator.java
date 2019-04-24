package jenerator.engine.generators;

import jenerator.annotations.reader.NaturalNumberConstraints;

public class ShortGenerator extends NaturalNumberGenerator<Short> {

	public ShortGenerator(NaturalNumberConstraints constraints) {
		super(constraints);
	}

	@Override
	public Short getValue() {
		return super.getRandomValue();
	}

}