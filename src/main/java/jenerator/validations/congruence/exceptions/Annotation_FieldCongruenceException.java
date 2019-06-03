package jenerator.validations.congruence.exceptions;

import java.lang.reflect.Field;

public class Annotation_FieldCongruenceException extends CongruenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Annotation_FieldCongruenceException() {
		this("Annotation does not match with the type of the field attached on");
		// TODO Auto-generated constructor stub
	}

	public Annotation_FieldCongruenceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public Annotation_FieldCongruenceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public Annotation_FieldCongruenceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public Annotation_FieldCongruenceException(Field field) {
		this("The field " + field.getName() + " in class " + field.getDeclaringClass().getCanonicalName()
				+ " has a no correct annotation attached on");
	}

	public Annotation_FieldCongruenceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
