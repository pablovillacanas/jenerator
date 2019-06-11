package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jenerator.annotations.constraints.BooleanConstraints;
import jenerator.annotations.constraints.CommonConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;

public class BooleanGenerator extends ValueGenerator<Boolean> {

	private BooleanConstraints constraints;

	public BooleanGenerator(long quantity, BooleanConstraints constraints) {
		super(quantity, new CommonConstraints());
		this.constraints = constraints;
		if (constraints == null) {
			this.constraints = new BooleanConstraints();
		}
	}

	@Override
	protected long getPossibilities() {
		return 2;
	}

	@Override
	public void valuesRandomGenerator() {
		if (constraints.getNullable() != 0) {
			int numberofnulls = (int) (constraints.getNullable() * getQuantity());
			for (int i = 0; i < numberofnulls; i++) {
				getValueContainer().add(null);
			}
		}
		if (constraints.getRelationTrueFalse() < 0) {
			while (!containerIsFilled()) {
				boolean nextBoolean = random.getRandomGenerator().nextBoolean();
				getValueContainer().add(nextBoolean);
			}
		} else if (constraints.getRelationTrueFalse() > 0 && constraints.getRelationTrueFalse() < 1) {
			int trueToGenerate = (int) ((getQuantity() - getValueContainer().size())
					* constraints.getRelationTrueFalse());
			for (int i = 0; i < trueToGenerate; i++) {
				getValueContainer().add(true);
			}
			while (!containerIsFilled()) {
				getValueContainer().add(false);
			}
		} else {
			while (!containerIsFilled()) {
				getValueContainer().add(false);
			}
		}
		List<Boolean> valueContainer = getValueContainer().stream().collect(Collectors.toList());
		Collections.shuffle(valueContainer);
	}

	@Override
	public List<Boolean> loadAllValues() {
		List<Boolean> boolean1 = new ArrayList<Boolean>();
		boolean1.add(true);
		boolean1.add(false);
		return boolean1;
	}

	@Override
	public Collection<Boolean> generateFromSource() throws CoverageExceededException, ElementFromSourceException {
		return null;
	}
}
