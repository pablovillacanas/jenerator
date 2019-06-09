package jenerator.validations.exceptions;

public class NoEmptyConstructorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoEmptyConstructorException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoEmptyConstructorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoEmptyConstructorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoEmptyConstructorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoEmptyConstructorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
