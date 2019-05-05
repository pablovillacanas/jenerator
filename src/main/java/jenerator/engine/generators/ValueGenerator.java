package jenerator.engine.generators;

import jenerator.annotations.constraints.Constraints;

//Could have like a memory to ensure unique is unique, etc?
public abstract class ValueGenerator<T>{

	protected Constraints constraints;

	public abstract T getValue();

}
