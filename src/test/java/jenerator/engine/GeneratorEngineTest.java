package jenerator.engine;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import jenerator.Foo;
import jenerator.Jenerator;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class GeneratorEngineTest {

	@Test
	public void generate() throws FieldValidationException, NoEmptyConstructorException,
			Annotation_FieldCongruenceException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NotAnnotationEncountered{
		Foo generateOne = (Foo) Jenerator.generateOne(Foo.class);
		System.out.println(generateOne);
	}
}
