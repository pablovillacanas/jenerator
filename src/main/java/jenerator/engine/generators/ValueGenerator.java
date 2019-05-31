package jenerator.engine.generators;

import java.util.Collection;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.Constraints;
import jenerator.engine.exceptions.CoverageExceededException;

public abstract class ValueGenerator<T extends Object> {

	protected static RandomDataGenerator random = new RandomDataGenerator();
	private Collection<T> valueContainer;
	protected Constraints constraints;
	/**
	 * <p>
	 * Number of elements the developer wants to be generated.
	 * </p>
	 */
	private long quantity;

	public static final double CRITICAL_COVERAGE = 0.75;

	public ValueGenerator(long quantity, Constraints constraints) {
		this.constraints = constraints;
		this.quantity = quantity;
	}

	/**
	 * <p>
	 * Calculates the coverage of values desired by the constraints. Must be
	 * override due to different kinds of calculations that have to be implemented
	 * in each generator type. The calculation takes the quantity of instances that
	 * developer want to generate and gets the relation with the possibilities that
	 * constraints allows to.
	 * </p>
	 * 
	 * @return A number, tipically between 0.0 and 1.0 that
	 */
	protected abstract double calculateCoverage() throws CoverageExceededException;

	/**
	 * <p>
	 * Adds the null elements by nullable constraint definition.
	 * </p>
	 * 
	 */
	protected void addNullElements() {
		long nullElements = (long) (quantity * 1 - constraints.getNullable());
		for (int i = 0; i < nullElements; i++) {
			valueContainer.add(null);
		}
	}

	/**
	 * <p>
	 * Performs an operation to calculate how many elements the generator has to
	 * create subtracting the null ones setted by nullable constraint.
	 * </p>
	 * 
	 * @return the number of values that has to be generated to achieve the quantity
	 *         of values desired.
	 */
	protected long getValuesToGenerate() {
		long toGenerate = (long) (quantity - quantity * constraints.getNullable());
		return toGenerate;
	}

	/**
	 * Control if the container has the same number of elements the developer
	 * require.
	 * 
	 * @return true if container is filled, false otherwise
	 */
	protected boolean containerIsFilled() {
		return valueContainer.size() == quantity;
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
		if (valueContainer.isEmpty() && constraints.getNullable() != 0.0)
			addNullElements();
	}

	public abstract Collection<T> generate() throws CoverageExceededException;

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getQuantity() {
		return quantity;
	}
}
