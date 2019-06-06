package jenerator.engine.parser.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jenerator.engine.generators.exceptions.SourceNotFoundException;
import jenerator.engine.parser.Source;

public class PlainDocument extends Source {

	private String extension;
	private char[] separator;

	public PlainDocument(File file) throws SourceNotFoundException {
		super();
		try {
			setInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new SourceNotFoundException(this, e);
		}
		setExtension(file.getName().substring(file.getName().lastIndexOf(".")));
		setDefaultSeparator();
	}

	private void setDefaultSeparator() {
		String tseparator = " ";
		if (extension.equals(".txt")) {
			tseparator = System.lineSeparator();
		}
		if (extension.equals(".csv")) {
			tseparator = ",";
		}
		separator = tseparator.toCharArray();
		setSeparator(separator);
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public char[] getSeparator() {
		return separator;
	}

	public void setSeparator(char[] separator) {
		this.separator = separator;
	}

}
