package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.constraints.Constraints;

public interface AnnotationReader<A extends Annotation> {

	Constraints readValues(A annotation);

}
