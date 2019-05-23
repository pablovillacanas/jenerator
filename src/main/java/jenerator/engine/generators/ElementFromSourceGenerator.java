package jenerator.engine.generators;

import java.util.Collection;

import com.google.common.collect.Streams;

import jenerator.annotations.constraints.CommonConstraints;
import jenerator.parser.Source;
import jenerator.parser.SourceReader;

public abstract class ElementFromSourceGenerator<E, S extends Source> extends ValueGenerator<E> {

	private SourceReader<S> sourceReader;
	private Collection<E> values;
	private CommonConstraints constraints;
	
	public ElementFromSourceGenerator(CommonConstraints constraints, SourceReader<S> sourceReader) {
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
