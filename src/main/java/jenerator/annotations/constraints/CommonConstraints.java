package jenerator.annotations.constraints;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class CommonConstraints extends Constraints {

	private String source;
	private Boolean unique;
	private Double nullable;

	public CommonConstraints() {
		super();
	}

	public void setCommonConstraints(CommonConstraints commonConstraints) {
		this.source = commonConstraints.getSource();
		this.unique = commonConstraints.getUnique();
		this.nullable = commonConstraints.getNullable();
	}

	public String getSource() {
		return source;
	}

	/**
	 * 
	 * @return The URL of the resource desired to obtain the values.
	 */
	public File getSourceAsFile() {
		// TOO if there is not under resources folder.
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
