package jenerator.engine;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import jenerator.Foo;
import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;



public class GeneratorEngineTest {

	@Test
	public void generate()
			throws FieldValidationException, NoEmptyConstructorException, AnnotationMismatchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		GeneratorEngine generatorEngine = new GeneratorEngine();
		Foo generateOne = (Foo) generatorEngine.generateOne(Foo.class);
		System.out.println(generateOne.getI());
	}
}