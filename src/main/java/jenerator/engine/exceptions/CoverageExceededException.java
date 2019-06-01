package jenerator.engine.exceptions;

/**
 * @author Pablo Villacanas
 *
 */
public class CoverageExceededException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoverageExceededException() {
		super();
	}

	public CoverageExceededException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CoverageExceededException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoverageExceededException(long quantity, int desired) {
		super("The number of instances desired is " + quantity + " but the posibilities are " + desired);
	}

	public CoverageExceededException(Throwable cause) {
		super(cause);
	}

}
