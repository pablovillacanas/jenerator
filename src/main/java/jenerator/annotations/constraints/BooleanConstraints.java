package jenerator.annotations.constraints;

public class BooleanConstraints extends Constraints {

	private double relationTrueFalse = 0.5d;
	private double nullable = 0.0d;

	/**
	 * @return the relationTrueFalse
	 */
	public double getRelationTrueFalse() {
		return relationTrueFalse;
	}

	/**
	 * @param relationTrueFalse the relationTrueFalse to set
	 */
	public void setRelationTrueFalse(double relationTrueFalse) {
		this.relationTrueFalse = relationTrueFalse;
	}

	/**
	 * @return the nullable
	 */
	public double getNullable() {
		return nullable;
	}

	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable(double nullable) {
		this.nullable = nullable;
	}
}
