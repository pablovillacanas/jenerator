package jenerator.engine.parser;

public class SourceNotFoundException extends ElementFromSourceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SourceNotFoundException(Source source, Throwable cause) {
		super("Source " + source + " was not found. Unable to generate elements", cause);
	}

	public SourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SourceNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
