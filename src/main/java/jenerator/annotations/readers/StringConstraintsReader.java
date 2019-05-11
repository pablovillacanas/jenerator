package jenerator.annotations.readers;

import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.StringConstraints;

public class StringConstraintsReader extends CommonConstraintsReader implements IAnnotationReader<StringGenerable> {

	StringConstraints stringConstraints;

	public StringConstraintsReader() {
		this.stringConstraints = new StringConstraints();
	}

	@Override
	public StringConstraints readValues(StringGenerable annotation) {
		stringConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		stringConstraints.setMinLenght(annotation.minLenght());
		stringConstraints.setMaxLenght(annotation.maxLenght());
		stringConstraints.setStringSimpleFormat(annotation.style());
		return stringConstraints;
	}
}
