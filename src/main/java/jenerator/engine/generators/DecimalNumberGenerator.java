package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.random.RandomDataGenerator;

import com.google.common.collect.Streams;

import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class DecimalNumberGenerator<E extends Number> extends ValueGenerator<Number> {

	private DecimalNumberConstraints constraints;
	private Class<?> numberType;

	public DecimalNumberGenerator(Class<?> numberType, long quantity, DecimalNumberConstraints constraints) {
		// TODO es necesario el tema de llevar al padre las constraints si despues las
		// sobreescribimos? Piensalo.
		super(quantity, constraints);
		this.constraints = constraints;
		if (this.constraints == null) {
			this.constraints = new DecimalNumberConstraints();
		}
		this.numberType = numberType;
	}

	@Override
	public List<Number> loadAllValues() {
		List<Number> allValues = new ArrayList<Number>();
		double minValue = constraints.getMinValue() * Math.pow(10, constraints.getPrecision());
		double maxValue = constraints.getMaxValue() * Math.pow(10, constraints.getPrecision());
		double precision = Math.pow(10, constraints.getPrecision());
		for (double i = minValue; i < maxValue; i++) {
			allValues.add((Number) (i / precision));
		}
		return allValues;
	}

	@Override
	public void valuesRandomGenerator() {
		while (!containerIsFilled()) {
			double minValue = constraints.getMinValue() * Math.pow(10, constraints.getPrecision());
			double maxValue = constraints.getMaxValue() * Math.pow(10, constraints.getPrecision());
			RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
			long nextLong = randomDataGenerator.nextLong((long) minValue, (long) maxValue);
			double value = nextLong / Math.pow(10, constraints.getPrecision());
			if (Double.class.isAssignableFrom(numberType)) {
				getValueContainer().add(value);
			}
			if (Float.class.isAssignableFrom(numberType)) {
				getValueContainer().add(Double.valueOf(value).floatValue());
			}
		}
	}

	@Override
	protected long getPossibilities() {
		long possibilities = (long) ((constraints.getMaxValue() - constraints.getMinValue())
				* Math.pow(10, constraints.getPrecision()));
		return possibilities;
	}

	@Override
	public Collection<Number> generateFromSource() throws CoverageExceededException, ElementFromSourceException {
		initSourceReader();

		Stream<String> stream = Streams.stream(sourceReader.iterator());
		List<Double> collectedDoublePossibilities = null;
		Function<String, Double> parseToDouble = (string) -> {
			if (!string.isEmpty()) {
				try {
					Double valueOf = Double.valueOf(Double.parseDouble(string));
					return valueOf;
				} catch (NumberFormatException e) {
				}
			}
			return null;
		};
		Predicate<? super Double> filterByConstraints = (number) -> {
			if (number != null && number <= constraints.getMaxValue() && number >= constraints.getMinValue())
				return true;
			else
				return false;
		};
		collectedDoublePossibilities = stream.map(parseToDouble).filter(filterByConstraints)
				.collect(Collectors.toList());

		Collection<Number> collectedPossibilities = new ArrayList<Number>();
		if (Double.class.isAssignableFrom(numberType)) {
			// We don't have to do nothing at this point unless...
			collectedPossibilities.addAll(collectedDoublePossibilities);
		} else if (Float.class.isAssignableFrom(numberType)) {
			collectedPossibilities = collectedDoublePossibilities.stream().map(doubleNumber -> {
				if (doubleNumber > Float.MIN_VALUE || doubleNumber < Float.MAX_VALUE)
					return Float.valueOf(doubleNumber.floatValue());
				else
					return null;
			}).collect(Collectors.toList());
		}

		if (!constraints.getUnique()) {
			setValueContainer(new ArrayList<Number>());
			return collectedPossibilities;
		} else {
			collectedPossibilities = collectedPossibilities.stream().collect(Collectors.toSet());
			if (collectedDoublePossibilities.size() < getValuesToGenerate()) {
				throw new CoverageExceededException(getQuantity(), collectedPossibilities.size());
			}
		}
		return collectedPossibilities;
	}
}
