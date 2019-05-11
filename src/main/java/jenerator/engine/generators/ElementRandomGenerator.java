package jenerator.engine.generators;

import com.google.common.collect.Streams;

import jenerator.annotations.constraints.Constraints;
import jenerator.parser.SourceReader;

public class ElementRandomGenerator extends ValueGenerator<String> {

	@SuppressWarnings("rawtypes")
	private SourceReader sourceReader;

	@SuppressWarnings("rawtypes")
	public ElementRandomGenerator(SourceReader sourceReader) {
		this.sourceReader = sourceReader;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getValue(Constraints constraints) {
		// TODO apply constraints
		String element = "";
		element = (String) Streams.stream(sourceReader.iterator())
				.skip((int) (sourceReader.getNumberOfItems() * Math.random())).findFirst().get();
		return element.trim();
	}
}
