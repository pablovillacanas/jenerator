package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;

public class DecimalNumberGenerator<E extends Number> extends ValueGenerator<Number> {

	private DecimalNumberConstraints constraints;
	private Class<?> numberType;

	public DecimalNumberGenerator(Class<?> numberType, long quantity, DecimalNumberConstraints constraints) {
		this(quantity, constraints);
		this.constraints = constraints;
		this.numberType = numberType;
	}

	private DecimalNumberGenerator(long quantity, Constraints constraints) {
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
					addValue(getRandomDecimalNumber(numberType));
				}
			}
		} else {
			setValueContainer(new ArrayList<Number>());
			while (!containerIsFilled()) {
				addValue(getRandomDecimalNumber(numberType));
			}
		}
		return getValueContainer();
	}

	private void loadAllValues() {
		double minValue = constraints.getMinValue() * Math.pow(10, constraints.getPrecision());
		double maxValue = constraints.getMaxValue() * Math.pow(10, constraints.getPrecision());
		double precision = Math.pow(10, constraints.getPrecision());
		for (double i = minValue; i < maxValue; i++) {
			addValue((Number) (i / precision));
		}
	}

	private Number getRandomDecimalNumber(Class<?> type) {
		double minValue = constraints.getMinValue() * Math.pow(10, constraints.getPrecision());
		double maxValue = constraints.getMaxValue() * Math.pow(10, constraints.getPrecision());
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		long nextLong = randomDataGenerator.nextLong((long) minValue, (long) maxValue);
		double result = nextLong / Math.pow(10, constraints.getPrecision());
		if (Float.class.isAssignableFrom(numberType)) {
			return (float) result;
		}
		return result;
	}

	@Override
	protected long getPossibilities() {
		long possibilities = (long) ((constraints.getMaxValue() - constraints.getMinValue())
				* Math.pow(10, constraints.getPrecision()));
		return possibilities;
	}
}
