package jenerator.parser.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jenerator.parser.Source;

public class PlainDocument extends Source {

	private File file;
	private String extension;
	private char[] separator;

	public PlainDocument(File file) throws FileNotFoundException {
		super(new FileInputStream(file));
		this.setFile(file);
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public char[] getSeparator() {
		return separator;
	}

	public void setSeparator(char[] separator) {
		this.separator = separator;
	}

}
