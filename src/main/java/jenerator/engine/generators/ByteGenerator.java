package jenerator.engine.generators;

import jenerator.annotations.reader.NaturalNumberGeneratorReader;

public class ByteGenerator extends NaturalNumberGenerator<Byte> {

	public ByteGenerator(NaturalNumberGeneratorReader reader) {
		super(reader);
	}

	@Override
	public Byte getValue() {
		return super.getRandomValue();
	}

}
