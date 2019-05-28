package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import jenerator.annotations.constraints.NaturalNumberConstraints;

public class NaturalNumberGenerator extends ValueGenerator<Number> {

	NaturalNumberConstraints constraints;

	public NaturalNumberGenerator(long quantity, NaturalNumberConstraints constraints) {
		super(quantity, constraints);
		this.constraints = constraints;
	}

	@Override
	public Collection<Number> generate() {
		if (constraints.getUnique()) {
			setValueContainer(new HashSet<Number>());
			if (calculateCoverage() >= CRITICAL_COVERAGE) {
				loadAllValues();
				List<Number> valuesList = new ArrayList<Number>(getValueContainer());
				List<Number> subList = valuesList.subList(0, (int) getQuantity());
				Collections.shuffle(valuesList);
				setValueContainer(subList);
			} else {
				while (getValuesToGenerate() > 0) {
					long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
					addValue(nextLong);
				}
			}
		} else {
			setValueContainer(new ArrayList<Number>());
			while (getValuesToGenerate() > 0) {
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
	protected double calculateCoverage() {
		long possiblilities = constraints.getMaxValue() - constraints.getMinValue();
		double toGenerate = getQuantity() - getValueContainer().size();
		return toGenerate / possiblilities;
	}
}
