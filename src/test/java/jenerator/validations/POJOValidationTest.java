package jenerator.validations;

import org.junit.Test;

import jenerator.validations.POJOValidationTestsCases.ClassWellFormedWithExplicitConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWellFormedWithoutExplicitConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutCorrectModifierConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutEmptyConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutSetter;
import jenerator.validations.exceptions.POJOValidationException;

public class POJOValidationTest {

	@Test
	public void testAllOk() throws POJOValidationException {
		POJOValidator.isPOJO(ClassWellFormedWithExplicitConstructor.class);
	}

	@Test
	public void testAllOk2() throws POJOValidationException {
		POJOValidator.isPOJO(ClassWellFormedWithoutExplicitConstructor.class);
	}

	@Test(expected = POJOValidationException.class)
	public void testGettersAndSetters_NotSetter() throws POJOValidationException {
		POJOValidator.isPOJO(ClassWithoutSetter.class);
	}

	@Test(expected = POJOValidationException.class)
	public void testGettersAndSetters_NotGetter() throws POJOValidationException {
		POJOValidator.isPOJO(ClassWithoutSetter.class);
	}

	@Test(expected = POJOValidationException.class)
	public void test_NotConstructor() throws POJOValidationException {
		POJOValidator.isPOJO(ClassWithoutEmptyConstructor.class);
	}

	@Test(expected = POJOValidationException.class)
	public void test_NotWellFormedConstructor() throws POJOValidationException {
		POJOValidator.isPOJO(ClassWithoutCorrectModifierConstructor.class);
	}
}
