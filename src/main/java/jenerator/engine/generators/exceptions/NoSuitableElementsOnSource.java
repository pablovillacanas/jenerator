package jenerator.engine.generators.exceptions;

/**
 * <p>
 * This exception is thrown when there are not elements that fits with the
 * constraints in the source
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class NoSuitableElementsOnSource extends ElementFromSourceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuitableElementsOnSource() {
		this("It seems that in source especified there is no values that match constraints you have defined");
		// TODO Auto-generated constructor stub
	}

	public NoSuitableElementsOnSource(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoSuitableElementsOnSource(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoSuitableElementsOnSource(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoSuitableElementsOnSource(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
