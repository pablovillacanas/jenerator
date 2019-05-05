package jenerator.annotations.readers;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.CommonConstraints;

public abstract class CommonConstraintsReader {

	CommonConstraints commonConstraints;

	public CommonConstraintsReader() {
		this.commonConstraints = new CommonConstraints() {
		};
	}

	CommonConstraints readValues(GenerationConstraints ann) {
		commonConstraints.setSource(ann.source());
		commonConstraints.setUnique(ann.unique());
		commonConstraints.setNullable(ann.nullable());
		return commonConstraints;
	}

}
