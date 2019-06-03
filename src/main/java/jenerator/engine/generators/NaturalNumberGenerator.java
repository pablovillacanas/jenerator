package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.math3.exception.NumberIsTooLargeException;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;

public class NaturalNumberGenerator<E> extends ValueGenerator<Number> {

	private NaturalNumberConstraints constraints;
	private Class<?> numberType;

	public NaturalNumberGenerator(Class<?> numberType, long quantity, NaturalNumberConstraints constraints) {
		this(quantity, constraints);
		this.constraints = constraints;
		this.numberType = numberType;
	}

	private NaturalNumberGenerator(long quantity, Constraints constraints) {
		super(quantity, constraints);
	}

	@Override
	public Collection<Number> generate() throws NumberIsTooLargeException, CoverageExceededException, NoSuitableElementsOnSource {
		if (constraints.getUnique()) {
			setValueContainer(new HashSet<Number>());
			if (calculateCoverage() >= CRITICAL_COVERAGE) {
				loadAllValues();
				List<Number> valuesList = new ArrayList<Number>(getValueContainer());
				List<Number> subList = valuesList.subList(0, (int) getQuantity());
				Collections.shuffle(valuesList);
				setValueContainer(subList);
			} else {
				while (!containerIsFilled()) {
					addValue(getRandomNumber());
				}
			}
		} else {
			setValueContainer(new ArrayList<Number>());
			while (!containerIsFilled()) {
				addValue(getRandomNumber());
			}
		}
		return getValueContainer();
	}

	private void loadAllValues() {
		for (long i = constraints.getMinValue(); i < constraints.getMaxValue(); i++) {
			addValue((Number) i);
		}
	}

	private Number getRandomNumber() {
		long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
		if (Long.class.isAssignableFrom(numberType)) {
			return (Number) nextLong;
		} else if (Integer.class.isAssignableFrom(numberType)) {
			return (Number) Long.valueOf(nextLong).intValue();
		} else if (Short.class.isAssignableFrom(numberType)) {
			return (Number) Long.valueOf(nextLong).shortValue();
		} else if (Byte.class.isAssignableFrom(numberType)) {
			return (Number) Long.valueOf(nextLong).byteValue();
		}
		return nextLong;
	}

	@Override
	protected long getPossibilities() {
		return constraints.getMaxValue() - constraints.getMinValue();
	}
}
