package jenerator.engine.generators;

import java.util.Collection;

import jenerator.annotations.constraints.Constraints;
import jenerator.parser.Source;
import jenerator.parser.SourceReader;

public abstract class ElementFromSourceGenerator<E, S extends Source> extends ValueGenerator<E> {

	private SourceReader<S> sourceReader;
	private Collection<E> values;
<<<<<<< HEAD
	private Constraints constraints;
	
	public ElementFromSourceGenerator(Constraints constraints, SourceReader<S> sourceReader) {
=======
	private CommonConstraints constraints;

	public ElementFromSourceGenerator(long quantity, CommonConstraints constraints, SourceReader<S> sourceReader) {
		super(quantity, constraints);
>>>>>>> f40231106248c39c00179216bb4378900da1cda8
		this.constraints = constraints;
		this.sourceReader = sourceReader;
	}

//	@Override
//	public Collection<E> getValue() {
//		// TODO apply constraints
//		String element = "";
//		element = (String) Streams.stream(sourceReader.iterator())
//				.skip((int) (sourceReader.getNumberOfItems() * Math.random())).findFirst().get();
//		return element.trim();
//	}
}
