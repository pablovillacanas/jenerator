package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.CommonConstraints;

public abstract class ConstraintsAnnotationReader<A extends Annotation> implements AnnotationReader<A> {

	public CommonConstraints readValues(GenerationConstraints constraints) {
		CommonConstraints commonConstraints = new CommonConstraints();
		commonConstraints.setSource(constraints.source());
		commonConstraints.setUnique(constraints.unique());
		commonConstraints.setNullable(constraints.nullable());
		return commonConstraints;
	}
}
