package jenerator.annotations.readers;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.CommonConstraints;

public abstract class CommonConstraintsReader {

	public CommonConstraintsReader() {}

	CommonConstraints readValues(GenerationConstraints ann) {
		CommonConstraints commonConstraints = new CommonConstraints();
		commonConstraints.setSource(ann.source());
		commonConstraints.setUnique(ann.unique());
		commonConstraints.setNullable(ann.nullable());
		return commonConstraints;
	}

}
