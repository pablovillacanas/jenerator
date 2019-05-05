package jenerator.annotations.readers;

import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.exceptions.MinMaxConstraintException;

public class StringConstraintsReader extends CommonConstraintsReader implements IAnnotationReader<StringGenerable> {

	StringConstraints stringConstraints;

	public StringConstraintsReader() {
		this.stringConstraints = new StringConstraints();
	}

	@Override
	public StringConstraints readValues(StringGenerable annotation) throws MinMaxConstraintException {
		stringConstraints.setCommonConstraints(super.readValues(annotation.constraints()));
		stringConstraints.setMaxLenght(annotation.maxLenght());
		stringConstraints.setMinLenght(annotation.minLenght());
		stringConstraints.setStringSimpleFormat(annotation.style());

		if (annotation.minLenght() < annotation.maxLenght() || annotation.minLenght() == annotation.maxLenght()) {
		} else {
			restoreMinMaxCongruence();
		}
		return stringConstraints;
	}

	public void restoreMinMaxCongruence() throws MinMaxConstraintException {
		// We must discover what value has changed from default to set other one. If
		// both have been changed, we must throw an exception
		if (stringConstraints.getMinLenght() != StringGenerable.DEFAULT_MIN_VALUE
				&& stringConstraints.getMaxLenght() != StringGenerable.DEFAULT_MAX_VALUE) {
			throw new MinMaxConstraintException(
					String.format("Min lenght for string is %d while max lenght is %d. Set correct values.",
							stringConstraints.getMinLenght(), stringConstraints.getMinLenght()));
		} else if (stringConstraints.getMinLenght() != StringGenerable.DEFAULT_MIN_VALUE) {
			stringConstraints.setMaxLenght(stringConstraints.getMinLenght() + 5);
		}
	}
}
