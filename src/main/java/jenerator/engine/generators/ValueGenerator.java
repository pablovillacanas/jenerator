package jenerator.engine.generators;

import jenerator.annotations.reader.Constraints;

//Could have like a memory to ensure unique is unique, etc?
public abstract class ValueGenerator<T>{

	Constraints constraints;

	public ValueGenerator() {
	}

	public abstract T getValue();

}
