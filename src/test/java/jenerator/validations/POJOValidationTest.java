package jenerator.validations;

import org.junit.Test;

import jenerator.validations.POJOValidationTestsCases.ClassWellFormedWithExplicitConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWellFormedWithoutExplicitConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutCorrectModifierConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutEmptyConstructor;
import jenerator.validations.POJOValidationTestsCases.ClassWithoutSetter;
import jenerator.validations.pojo.POJOValidator;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class POJOValidationTest {

	@Test
	public void testAllOk() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidator.isPOJO(ClassWellFormedWithExplicitConstructor.class);
	}

	@Test
	public void testAllOk2() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidator.isPOJO(ClassWellFormedWithoutExplicitConstructor.class);
	}

	@Test(expected = FieldValidationException.class)
	public void testGettersAndSetters_NotSetter() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidator.isPOJO(ClassWithoutSetter.class);
	}

	@Test(expected = FieldValidationException.class)
	public void testGettersAndSetters_NotGetter() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidator.isPOJO(ClassWithoutSetter.class);
	}

	@Test(expected = NoEmptyConstructorException.class)
	public void test_NotConstructor() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidator.isPOJO(ClassWithoutEmptyConstructor.class);
	}

	@Test(expected = NoEmptyConstructorException.class)
	public void test_NotWellFormedConstructor() throws FieldValidationException, NoEmptyConstructorException {
		POJOValidator.isPOJO(ClassWithoutCorrectModifierConstructor.class);
	}
}
