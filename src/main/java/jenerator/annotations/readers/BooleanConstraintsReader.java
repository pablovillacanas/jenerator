package jenerator.annotations.readers;

import jenerator.annotations.BooleanGenerable;
import jenerator.annotations.constraints.BooleanConstraints;
import jenerator.annotations.constraints.Constraints;

public class BooleanConstraintsReader extends ConstraintsAnnotationReader<BooleanGenerable> {

	BooleanConstraints booleanConstraints = new BooleanConstraints();

	@Override
	public Constraints readValues(BooleanGenerable annotation) {
		booleanConstraints.setRelationTrueFalse(annotation.relationTrueFalse());
		booleanConstraints.setNullable(annotation.nullable());
		return booleanConstraints;
	}

}
