package jenerator.engine.generators.exceptions;

public class NoSuitableElementsOnSource extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuitableElementsOnSource() {
		this("It seems that in source especified there is no values that match constrints you defined");
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
