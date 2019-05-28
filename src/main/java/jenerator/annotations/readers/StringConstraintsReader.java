package jenerator.annotations.readers;

import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.StringConstraints;

public class StringConstraintsReader<A extends StringGenerable> extends CommonConstraintsReader<A> {

	private StringConstraints stringConstraints;

	public StringConstraintsReader() {
		this.stringConstraints = new StringConstraints();
	}

	@Override
	public Constraints readValues(StringGenerable annotation) {
		stringConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		stringConstraints.setMinLenght(annotation.minLenght());
		stringConstraints.setMaxLenght(annotation.maxLenght());
		stringConstraints.setStringSimpleFormat(annotation.style());
		return stringConstraints;
	}
}
