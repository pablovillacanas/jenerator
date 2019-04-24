package jenerator.annotations.reader;

import java.lang.annotation.Annotation;

public interface IAnnotationReader<T extends Annotation> {

	Constraints readValues(T annotation);
}
