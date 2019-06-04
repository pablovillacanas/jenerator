package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.Constraints;

public abstract class ConstraintsReader<A extends Annotation> implements IAnnotationReader<A> {

	private Constraints constraints;

	public Constraints readValues(GenerationConstraints generationConstraints) {
		constraints = new Constraints();
		constraints.setSource(generationConstraints.source());
		constraints.setUnique(generationConstraints.unique());
		constraints.setNullable(generationConstraints.nullable());
		return constraints;
	}
}
