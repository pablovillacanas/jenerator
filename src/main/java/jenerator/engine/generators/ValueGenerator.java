package jenerator.engine.generators;

import java.util.Collection;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.CommonConstraints;

public abstract class ValueGenerator<T extends Object> {

	protected static RandomDataGenerator random = new RandomDataGenerator();
	private Collection<T> valueContainer;
	protected CommonConstraints commonConstraints;
	/**
	 * <p>
	 * Number of elements the developer wants to be generated.
	 * </p>
	 */
	private long quantity;

	public static final double CRITICAL_COVERAGE = 0.75;

	public ValueGenerator(long quantity, CommonConstraints commonConstraints) {
		super();
		this.commonConstraints = commonConstraints;
		this.quantity = quantity;
	}

	public long getQuantity() {
		return quantity;
	}

	/**
	 * <p>
	 * Calculates the coverage of values desired by the constraints. Must be
	 * override due to different kinds of calculations that have to be implemented
	 * in each generator type.
	 * </p>
	 * 
	 * @return
	 */
	protected abstract double calculateCoverage();

	/**
	 * <p>
	 * Adds the null elements by nullable constraint definition.
	 * </p>
	 * 
	 * @return
	 */
	protected void addNullElements() {
		long nullElements = (long) (quantity * 1 - commonConstraints.getNullable());
		for (int i = 0; i < nullElements; i++) {
			valueContainer.add(null);
		}
	}

	/**
	 * <p>
	 * Returns the num of values that has to be generated to achieve the quantity of
	 * values desired.
	 * </p>
	 * 
	 * @return
	 */
	protected long getValuesToGenerate() {
		return quantity - valueContainer.size();
	}

	/**
	 * <p>
	 * Returns a value and removes it from the value container
	 * </p>
	 * 
	 * @return
	 */
	public T getValue() {
		T t = getValueContainer().stream().findAny().get();
		getValueContainer().remove(t);
		return t;
	}

	/**
	 * <p>
	 * Adds a value to the container value
	 * </p>
	 * 
	 * @param value
	 */
	public void addValue(T value) {
		valueContainer.add(value);
	}

	protected Collection<T> getValueContainer() {
		return valueContainer;
	}

	protected void setValueContainer(Collection<T> valueContainer) {
		this.valueContainer = valueContainer;
		if (valueContainer.isEmpty() && commonConstraints.getNullable() != 0.0)
			addNullElements();
	}

	public abstract Collection<T> generate();
}
