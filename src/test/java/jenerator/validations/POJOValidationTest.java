package jenerator.validations;

import org.junit.Test;

import jenerator.validations.POJOValidationTestsCases.ClassWellFormedWithExplicitConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWellFormedWithoutExplicitConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutCorrectModifierConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutEmptyConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutSetter;
import jenerator.validations.pojo.POJOValidation;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class POJOValidationTest {

	@Test
	public void testAllOk() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.isPOJO(ClassWellFormedWithExplicitConstructor.class);
	}

	@Test
	public void testAllOk2() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.isPOJO(ClassWellFormedWithoutExplicitConstructor.class);
	}

	@Test(expected = FieldValidationException.class)
	public void testGettersAndSetters_NotSetter() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.isPOJO(ClassWithoutSetter.class);
	}

	@Test(expected = FieldValidationException.class)
	public void testGettersAndSetters_NotGetter() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.isPOJO(ClassWithoutSetter.class);
	}

	@Test(expected = NoEmptyConstructorException.class)
	public void test_NotConstructor() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.isPOJO(ClassWithoutEmptyConstructor.class);
	}

	@Test(expected = NoEmptyConstructorException.class)
	public void test_NotWellFormedConstructor() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.isPOJO(ClassWithoutCorrectModifierConstructor.class);
	}
}
