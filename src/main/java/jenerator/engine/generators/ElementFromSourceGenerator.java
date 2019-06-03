package jenerator.engine.generators;

import jenerator.annotations.constraints.Constraints;
import jenerator.engine.parser.Source;
import jenerator.engine.parser.SourceReader;

public abstract class ElementFromSourceGenerator<E, S extends Source> extends ValueGenerator<E> {

	protected SourceReader<S> sourceReader;

	public ElementFromSourceGenerator(long quantity, Constraints constraints, SourceReader<S> sourceReader) {
		super(quantity, constraints);
		this.constraints = constraints;
		this.sourceReader = sourceReader;
	}

}
