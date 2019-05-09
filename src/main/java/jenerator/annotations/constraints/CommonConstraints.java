package jenerator.annotations.constraints;

import jenerator.annotations.GenerationConstraints;

public class CommonConstraints extends Constraints {

	private String source;
	private Boolean unique;
	private Double nullable;

	void readValues(GenerationConstraints ann) {
		this.source = ann.source();
		this.unique = ann.unique();
		this.nullable = ann.nullable();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public Double getNullable() {
		return nullable;
	}

	public void setNullable(Double nullable) {
		this.nullable = nullable;
	}

}
