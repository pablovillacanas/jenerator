package jenerator.validations.congruence.exceptions;

public class AnnotationMismatchFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnnotationMismatchFieldException() {
		this("Annotation does not match with the type of the field attached on");
		// TODO Auto-generated constructor stub
	}

	public AnnotationMismatchFieldException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AnnotationMismatchFieldException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AnnotationMismatchFieldException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AnnotationMismatchFieldException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
