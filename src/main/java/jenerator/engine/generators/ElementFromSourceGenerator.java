package jenerator.engine.generators;

import jenerator.annotations.constraints.CommonConstraints;
import jenerator.engine.parser.Source;
import jenerator.engine.parser.SourceReader;

public abstract class ElementFromSourceGenerator<E, S extends Source> extends ValueGenerator<E> {

	protected SourceReader<S> sourceReader;

	public ElementFromSourceGenerator(long quantity, CommonConstraints constraints, SourceReader<S> sourceReader) {
		super(quantity, constraints);
		this.constraints = constraints;
		this.sourceReader = sourceReader;
	}

}
