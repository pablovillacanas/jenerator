package jenerator.engine.generators;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import jenerator.annotations.constraints.StringConstraints;

public class CharGenerator extends ValueGenerator<Character> {

	StringConstraints constraints;

	protected CharGenerator(long quantity, StringConstraints constraints) {
		super(quantity, constraints);
		this.constraints = constraints;
	}

	@Override
	public Character getValue() {
		StringConstraints stringConstraints = (StringConstraints) constraints;
		List<Character> characters = stringConstraints.getStringSimpleFormat().getCharacters();
		int randomIndex = new Random().nextInt(characters.size());
		return characters.get(randomIndex);
	}

	@Override
	public Collection<Character> generate() {
		return null;
	}

	@Override
	protected double calculateCoverage() {
		long possiblilities = constraints.getStringSimpleFormat().characters.size();
		double toGenerate = getQuantity() - getValueContainer().size();
		return toGenerate / possiblilities;
	}
}
