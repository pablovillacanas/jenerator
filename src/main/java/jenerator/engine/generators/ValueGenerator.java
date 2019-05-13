package jenerator.engine.generators;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.CommonConstraints;

//Could have like a memory to ensure unique is unique, etc?
public abstract class ValueGenerator<T extends Object> {

	protected static RandomDataGenerator random = new RandomDataGenerator();

	@SuppressWarnings("unchecked")
	public final static <T extends Object> T getValue(Class<T> class1, CommonConstraints constraints) {
		if (Number.class.isAssignableFrom(class1)) {
			if (class1.isAssignableFrom(Long.class) || class1.isAssignableFrom(Integer.class)
					|| class1.isAssignableFrom(Short.class) || class1.isAssignableFrom(Byte.class)) {
				return (T) new NaturalNumberGenerator().getValue(constraints);
			} else {
//					new DecimalNumberGenerator().getValue(class1, constraints);
			}
		} else if (class1 == String.class) {
			return (T) new StringGenerator().getValue(constraints);
		}
		return null;
	}

	@SuppressWarnings("hiding")
	protected abstract <T extends Object> T getValue(CommonConstraints constraints);

}
