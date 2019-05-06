package jenerator.parser;

import java.io.InputStream;

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
