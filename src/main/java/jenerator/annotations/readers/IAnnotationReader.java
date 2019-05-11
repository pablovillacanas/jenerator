package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.constraints.CommonConstraints;

public interface IAnnotationReader<T extends Annotation> {

	CommonConstraints readValues(T annotation);
}
