package jenerator.annotations.constraints;

public class Constraints{

	private String source;
	private Boolean unique;
	private Double nullable;

	public void setCommonConstraints(Constraints constraints) {
		setNullable(constraints.getNullable());
		setUnique(constraints.getUnique());
		setSource(constraints.getSource());
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
