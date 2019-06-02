package jenerator.engine.parser;

import java.io.InputStream;

/**
 * <p>
 * Represents a source of elements (input stream) that provide values to the
 * ElementFromSourceGenerator.
 * </p>
 * 
 * @author pablo
 *
 */
public class Source {

	private InputStream inputStream;

	public Source(InputStream inputStream) {
		this.setInputStream(inputStream);
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
