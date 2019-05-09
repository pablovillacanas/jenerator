package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.constraints.Constraints;

public interface IAnnotationReader<T extends Annotation> {

	Constraints readValues(T annotation);
}
