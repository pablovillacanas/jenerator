package jenerator.annotations.readers;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.constraints.NaturalNumberConstraints;

public class NaturalNumberConstraintsReader extends CommonConstraintsReader
		implements IAnnotationReader<NaturalNumberGenerable> {

	NaturalNumberConstraints naturalNumberConstraints;

	public NaturalNumberConstraintsReader() {
		this.naturalNumberConstraints = new NaturalNumberConstraints();
	}

	@Override
	public NaturalNumberConstraints readValues(NaturalNumberGenerable annotation){
		naturalNumberConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		naturalNumberConstraints.setMinValue(annotation.minValue());
		naturalNumberConstraints.setMaxValue(annotation.maxValue());
		return naturalNumberConstraints;
	}
}
