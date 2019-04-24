package jenerator.engine.generators;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import jenerator.Foo;
import jenerator.engine.GeneratorController;
import jenerator.filters.exceptions.NotAnnotationEncountered;

public class GeneratorControllerTest {

	@Test
	public void setValueTest() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered {
		Foo foo = new Foo();
		GeneratorController generatorController = new GeneratorController(foo, Foo.class);
		generatorController.process();
		System.out.println(foo);
	}
}
