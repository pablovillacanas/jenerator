package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.random.RandomDataGenerator;

public abstract class ValueGenerator<T extends Object> {

	protected static RandomDataGenerator random = new RandomDataGenerator();
	private Collection<T> valueContainer;
//	private Class<T> classType;
	public static final double CRITICAL_COVERAGE = 0.75;

	public T getValue() {
		T t = getValueContainer().stream().findAny().get();
		getValueContainer().remove(t);
		return t;
	}

	public abstract Collection<T> generate(long quantity);

	public Collection<T> getValueContainer() {
		return valueContainer;
	}

	protected void setValueContainer(Collection<T> valueContainer) {
		this.valueContainer = valueContainer;
	}
}
