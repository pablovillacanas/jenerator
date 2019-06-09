package jenerator.validations.exceptions;

public class NoGenerableClassException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoGenerableClassException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoGenerableClassException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoGenerableClassException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoGenerableClassException(Class<?> class1) {
		super("The class " + class1.getCanonicalName() + " is not generable. It must be annotated as Generable.");

	}

	public NoGenerableClassException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
