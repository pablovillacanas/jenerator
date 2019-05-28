package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.Constraints;

public abstract class CommonConstraintsReader<A extends Annotation> implements IAnnotationReader<A> {

	private Constraints constraints;

	protected Constraints readValues(GenerationConstraints generationConstraints) {
		constraints = new Constraints();
		if (generationConstraints instanceof GenerationConstraints) {
			GenerationConstraints annotation = (GenerationConstraints) generationConstraints;
			constraints.setSource(annotation.source());
			constraints.setUnique(annotation.unique());
			constraints.setNullable(annotation.nullable());
		}
		return constraints;
	}

}
