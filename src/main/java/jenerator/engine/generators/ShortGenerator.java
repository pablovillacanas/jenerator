package jenerator.engine.generators;

import jenerator.annotations.reader.NaturalNumberGeneratorReader;

public class ShortGenerator extends NaturalNumberGenerator<Short> {

	public ShortGenerator(NaturalNumberGeneratorReader reader) {
		super(reader);
	}

	@Override
	public Short getValue() {
		return super.getRandomValue();
	}

}
