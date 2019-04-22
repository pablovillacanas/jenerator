package jenerator.validations;

import java.lang.reflect.Constructor;

import org.junit.Test;

import jenerator.models.POJOValidationTestsCases.ClassWellFormed;
import jenerator.models.POJOValidationTestsCases.ClassWithoutConstructor;
import jenerator.models.POJOValidationTestsCases.ClassWithoutCorrectModifierConstructor;
import jenerator.models.POJOValidationTestsCases.ClassWithoutSetter;
import jenerator.validations.pojo.POJOValidation;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class POJOValidationTest {

	@Test
	public void testAllOk() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.validate(ClassWellFormed.class);
	}

	@Test(expected = FieldValidationException.class)
	public void testGettersAndSetters_NotSetter() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.validate(ClassWithoutSetter.class);
	}

	@Test(expected = FieldValidationException.class)
	public void testGettersAndSetters_NotGetter() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.validate(ClassWithoutSetter.class);
	}

	@Test(expected = NoEmptyConstructorException.class)
	public void test_NotConstructor() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.validate(ClassWithoutConstructor.class);
	}

	@Test(expected = NoEmptyConstructorException.class)
	public void test_NotWellFormedConstructor() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidation.validate(ClassWithoutCorrectModifierConstructor.class);
	}
}
