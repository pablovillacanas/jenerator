package jenerator.validations;

import org.junit.Test;

import jenerator.models.ConcordancyValidationsTestCases;
import jenerator.validations.congruency.AnnotationsValidation;
import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;

public class ConcordancyValidationTest {

	@Test(expected = AnnotationMismatchFieldException.class)
	public void stringAnnotatedAsNatural() throws AnnotationMismatchFieldException {
		AnnotationsValidation.validate(ConcordancyValidationsTestCases.StringAnnotatedAsNatural.class);
	}

	@Test
	public void integerAnnotatedAsNatural() throws AnnotationMismatchFieldException {
		AnnotationsValidation.validate(ConcordancyValidationsTestCases.IntegerAnnotatedAsNatural.class);
	}
	
	@Test(expected = AnnotationMismatchFieldException.class)
	public void objectAnnotatedAsNatural() throws AnnotationMismatchFieldException {
		AnnotationsValidation.validate(ConcordancyValidationsTestCases.ObjectAnnotatedAsNatural.class);
	}
}
