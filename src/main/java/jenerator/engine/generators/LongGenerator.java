package jenerator.engine.generators;

import jenerator.annotations.reader.NaturalNumberConstraints;

public class LongGenerator extends NaturalNumberGenerator<Long> {

	public LongGenerator(NaturalNumberConstraints constraints) {
		super(constraints);
	}

	@Override
	public Long getValue() {
		return super.getRandomValue();
	}

}
