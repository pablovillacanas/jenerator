package jenerator.annotations.constraints;

public class CommonConstraints{

	private String source;
	private Boolean unique;
	private Double nullable;

	public void setCommonConstraints(CommonConstraints commonConstraints) {
		setNullable(commonConstraints.getNullable());
		setUnique(commonConstraints.getUnique());
		setSource(commonConstraints.getSource());
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
