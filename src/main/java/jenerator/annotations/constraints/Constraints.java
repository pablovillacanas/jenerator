package jenerator.annotations.constraints;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Constraints {

	private String source;
	private Boolean unique;
	private Double nullable;

	public void setCommonConstraints(Constraints constraints) {
		setNullable(constraints.getNullable());
		setUnique(constraints.getUnique());
		setSource(constraints.getSource());
	}

	public String getSource() {
		return source;
	}

	/**
	 * 
	 * @return The URL of the resource desired to obtain the values
	 */
	public File getSourceAsFile() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			URI uri = classLoader.getResource(getSource()).toURI();
			return new File(uri);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public Double getNullable() {
		return nullable;
	}

	public void setNullable(Double nullable) {
		this.nullable = nullable;
	}

}
