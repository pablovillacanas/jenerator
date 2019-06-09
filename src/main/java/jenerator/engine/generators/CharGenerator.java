package jenerator.engine.generators;

import java.util.Collection;
import java.util.List;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;

public class CharGenerator extends ValueGenerator<Character> {

	StringConstraints constraints;

	protected CharGenerator(long quantity, StringConstraints constraints) {
		super(quantity, constraints);
		if (constraints == null) {
			this.constraints = new StringConstraints();
		}
	}

	@Override
	protected long getPossibilities() {
		return constraints.getStringSimpleFormat().getCharacters().size();
	}

	@Override
	public Collection<Character> generateFromSource() throws CoverageExceededException, NoSuitableElementsOnSource {
		return null;
	}

	@Override
	public void valuesRandomGenerator() {
		int randomIndex = 0;
		while (!containerIsFilled()) {
			random.nextInt(0, constraints.getStringSimpleFormat().getCharacters().size());
			getValueContainer().add(constraints.getStringSimpleFormat().getCharacters().get(randomIndex));
		}
	}

	@Override
	public List<Character> loadAllValues() {
		return constraints.getStringSimpleFormat().getCharacters();
	}
}
