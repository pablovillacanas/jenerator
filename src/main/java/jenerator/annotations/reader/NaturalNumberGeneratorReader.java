package jenerator.annotations.reader;

import jenerator.annotations.NaturalNumberGenerable;

public class NaturalNumberGeneratorReader extends CommonConstraintsReader implements IAnnotationReader<NaturalNumberGenerable> {

	NaturalNumberConstraints naturalNumberConstraints;

	public NaturalNumberGeneratorReader() {
		this.naturalNumberConstraints = new NaturalNumberConstraints();
	}

	@Override
	public NaturalNumberConstraints readValues(NaturalNumberGenerable annotation) {
		naturalNumberConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		naturalNumberConstraints.setMinValue(annotation.minValue());
		naturalNumberConstraints.setMaxValue(annotation.maxValue());
		return naturalNumberConstraints;
	}
}
