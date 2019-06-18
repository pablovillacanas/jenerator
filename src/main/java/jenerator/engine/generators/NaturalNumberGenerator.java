package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class NaturalNumberGenerator<E> extends ValueGenerator<Number> {

	private NaturalNumberConstraints constraints;
	private Class<?> numberType;

	public NaturalNumberGenerator(Class<?> numberType, long quantity, NaturalNumberConstraints constraints) {
		super(quantity, constraints);
		this.constraints = constraints;
		if (this.constraints == null) {
			this.constraints = new NaturalNumberConstraints();
		}
		this.numberType = numberType;
	}

	@Override
	public void valuesRandomGenerator() {
		Number number = null;
		while (!containerIsFilled()) {
			long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
			if (Long.class.isAssignableFrom(numberType)) {
				number = nextLong;
			} else if (Integer.class.isAssignableFrom(numberType)) {
				number = Long.valueOf(nextLong).intValue();
			} else if (Short.class.isAssignableFrom(numberType)) {
				number = Long.valueOf(nextLong).shortValue();
			} else if (Byte.class.isAssignableFrom(numberType)) {
				number = Long.valueOf(nextLong).byteValue();
			}
			getValueContainer().add(number);
		}
	}

	@Override
	public List<Number> loadAllValues() {
		List<Number> allValues = new ArrayList<Number>();
		for (long i = constraints.getMinValue(); i < constraints.getMaxValue(); i++) {
			allValues.add(i);
		}
		return allValues;
	}

	@Override
	protected long getPossibilities() {
		return constraints.getMaxValue() - constraints.getMinValue();
	}

	@Override
	public Collection<Number> generateFromSource() throws CoverageExceededException, ElementFromSourceException {
		initSourceReader();

		Stream<String> stream = Streams.stream(sourceReader.iterator());
		List<Long> collectedLongPossibilities = null;
		Function<String, Long> parseToLong = (string) -> {
			if (!string.isEmpty()) {
				try {
					Long valueOf = Long.valueOf(Long.parseLong(string));
					return valueOf;
				} catch (NumberFormatException e) {
				}
			}
			return null;
		};
		Predicate<Long> filterByConstraints = (number) -> {
			if (number != null && number <= constraints.getMaxValue() && number >= constraints.getMinValue())
				return true;
			else
				return false;
		};
		collectedLongPossibilities = stream.map(parseToLong).filter(filterByConstraints).collect(Collectors.toList());

		Collection<Number> collectedPossibilities = new ArrayList<Number>();
		if (Long.class.isAssignableFrom(numberType)) {
			// We don't have to do nothing at this point unless...
			collectedPossibilities.addAll(collectedLongPossibilities);
		} else if (Integer.class.isAssignableFrom(numberType)) {
			collectedPossibilities = collectedLongPossibilities.stream().map(longNumber -> {
				if (longNumber > Integer.MIN_VALUE || longNumber < Integer.MAX_VALUE)
					return Integer.valueOf(longNumber.intValue());
				else
					return null;
			}).collect(Collectors.toList());
		} else if (Short.class.isAssignableFrom(numberType)) {
			collectedPossibilities = collectedLongPossibilities.stream().map(longNumber -> {
				if (longNumber > Short.MIN_VALUE || longNumber < Short.MAX_VALUE)
					return Short.valueOf(longNumber.shortValue());
				else
					return null;
			}).collect(Collectors.toList());
		} else if (Byte.class.isAssignableFrom(numberType)) {
			collectedPossibilities = collectedLongPossibilities.stream().map(longNumber -> {
				if (longNumber > Byte.MIN_VALUE || longNumber < Byte.MAX_VALUE)
					return Byte.valueOf(longNumber.byteValue());
				else
					return null;
			}).collect(Collectors.toList());
		}

		if (!constraints.getUnique()) {
			setValueContainer(new ArrayList<Number>());
			return collectedPossibilities;
		} else {
			collectedPossibilities = collectedPossibilities.stream().collect(Collectors.toSet());
			if (collectedLongPossibilities.size() < getValuesToGenerate()) {
				throw new CoverageExceededException(getQuantity(), collectedPossibilities.size());
			}
		}
		return collectedPossibilities;
	}

}
