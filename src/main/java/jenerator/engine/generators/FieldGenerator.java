package jenerator.engine.generators;

public abstract class FieldGenerator<T> implements ValueGenerator<T> {

	private String source;

	private boolean unique;

	private double nullable;

	public FieldGenerator() {
		source = null;
		unique = false;
		nullable = 0;
	}

	public FieldGenerator(String source, boolean unique, double nullable) {
		super();
		this.source = source;
		this.unique = unique;
		this.nullable = nullable;
	}

	protected String getSource() {
		return source;
	}

	protected void setSource(String source) {
		this.source = source;
	}

	protected boolean isUnique() {
		return unique;
	}

	protected void setUnique(boolean unique) {
		this.unique = unique;
	}

	protected double getNullable() {
		return nullable;
	}

	protected void setNullable(double nullable) {
		this.nullable = nullable;
	}

}
