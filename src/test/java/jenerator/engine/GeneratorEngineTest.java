package jenerator.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

import jenerator.Foo;
import jenerator.Jenerator;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.validations.congruence.exceptions.Annotation_FieldCongruenceException;
import jenerator.validations.pojo.exceptions.FieldValidationException;
import jenerator.validations.pojo.exceptions.NoEmptyConstructorException;

public class GeneratorEngineTest {

	@Test
	public void generate()
			throws FieldValidationException, NoEmptyConstructorException, Annotation_FieldCongruenceException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			SecurityException, NotAnnotationEncountered, CoverageExceededException {
		List<Foo> generate = Jenerator.generate(Foo.class, 1);
		System.out.println(generate.get(0));
	}
}
