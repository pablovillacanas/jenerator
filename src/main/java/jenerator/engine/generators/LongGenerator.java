package jenerator.engine.generators;

import jenerator.annotations.reader.NaturalNumberGeneratorReader;

public class LongGenerator extends NaturalNumberGenerator<Long> {

	public LongGenerator(NaturalNumberGeneratorReader reader) {
		super(reader);
	}

	@Override
	public Long getValue() {
		return super.getRandomValue();
	}

}
