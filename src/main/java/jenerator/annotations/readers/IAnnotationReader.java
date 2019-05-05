package jenerator.annotations.readers;

import java.lang.annotation.Annotation;

import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.readers.exceptions.AnnotationConstraintsException;

public interface IAnnotationReader<T extends Annotation> {

	Constraints readValues(T annotation) throws AnnotationConstraintsException;
}
