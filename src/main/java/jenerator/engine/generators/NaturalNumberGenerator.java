package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import jenerator.annotations.constraints.NaturalNumberConstraints;

public class NaturalNumberGenerator extends ValueGenerator<Number> {

	NaturalNumberConstraints constraints;

	public NaturalNumberGenerator(NaturalNumberConstraints constraints) {
		this.constraints = constraints;
	}

	@Override
	public Collection<Number> generate(long quantity) {
		if (constraints.getUnique()) {
			setValueContainer(new HashSet<Number>());
			long possiblilities = constraints.getMaxValue() - constraints.getMinValue();
			if (quantity / possiblilities >= CRITICAL_COVERAGE) {
				loadAllValues();
				List<Number> valuesList = new ArrayList<Number>(getValueContainer());
				Collections.shuffle(valuesList);
				List<Number> subList = valuesList.subList(0, (int) quantity);
				setValueContainer(subList);
			} else {
				while (getValueContainer().size() < quantity) {
					long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
					getValueContainer().add(nextLong);
				}
			}
		} else {
			setValueContainer(new ArrayList<Number>());
			for (int i = 0; i < quantity; i++) {
				long nextLong = random.nextLong(constraints.getMinValue(), constraints.getMaxValue());
				getValueContainer().add(nextLong);
			}
		}
		return getValueContainer();
	}

	private void loadAllValues() {
		for (long i = constraints.getMinValue(); i < constraints.getMaxValue(); i++) {
			getValueContainer().add(i);
		}
	}
}
