package jenerator.engine.generators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Test;

import jenerator.Foo;
import jenerator.engine.GeneratorController;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.filters.exceptions.NotAnnotationEncountered;

public class ValueGeneratorTest {

	@Test
	public void getValueTest() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NotAnnotationEncountered, CoverageExceededException {
		Foo foo = new Foo();
		Foo foo2 = new Foo();
		ArrayList<Foo> arrayList = new ArrayList<Foo>();
		arrayList.add(foo);
		arrayList.add(foo2);
		GeneratorController generatorController = new GeneratorController(Foo.class, arrayList);
		generatorController.process();
		System.out.println(foo);
	}
}
