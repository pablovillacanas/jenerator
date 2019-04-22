package jenerator.annotations.reader;

import jenerator.annotations.NaturalNumberGenerable;

public class NaturalNumberGeneratorReader extends CommonConstraintReader
		implements AnnotationReader<NaturalNumberGenerable> {

	long minValue;
	long maxValue;

	public NaturalNumberGeneratorReader(NaturalNumberGenerable annotation) {
		readValues(annotation);
	}

	public void readValues(NaturalNumberGenerable ann) {
		super.readValues(ann.constraints());
		maxValue = ann.maxValue();
		minValue = ann.minValue();
	}

	public long getMinValue() {
		return minValue;
	}

	public long getMaxValue() {
		return maxValue;
	}

}
