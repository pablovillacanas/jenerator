package jenerator.annotations.reader;

import jenerator.annotations.GenerationConstraints;

public abstract class CommonConstraintReader{

	String source;
	boolean unique;
	double nullable;

	public void readValues(GenerationConstraints ann) {
		source = ann.source();
		unique = ann.unique();
		nullable = ann.nullable();
	}

	public String getSource() {
		return source;
	}

	public boolean isUnique() {
		return unique;
	}

	public double getNullable() {
		return nullable;
	}
	
}
