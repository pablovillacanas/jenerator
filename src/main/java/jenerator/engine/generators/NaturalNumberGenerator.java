package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.math3.exception.NumberIsTooLargeException;

import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.engine.exceptions.CoverageExceededException;

public class NaturalNumberGenerator extends ValueGenerator<Number> {

	NaturalNumberConstraints constraints;

	public NaturalNumberGenerator(long quantity, NaturalNumberConstraints constraints) {
		super(quantity, constraints);
		this.constraints = constraints;
	}

	@Override
	public Collection<Number> generate() throws NumberIsTooLargeException, CoverageExceededException {
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
					long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
					addValue(nextLong);
				}
			}
		} else {
			setValueContainer(new ArrayList<Number>());
			while (!containerIsFilled()) {
				long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
				addValue(nextLong);
			}
		}
		return getValueContainer();
	}

	private void loadAllValues() {
		for (long i = constraints.getMinValue(); i < constraints.getMaxValue(); i++) {
			addValue(i);
		}
	}

	@Override
	protected long getPossibilities() {
		return constraints.getMaxValue() - constraints.getMinValue();
	}
}
