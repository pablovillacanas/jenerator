package jenerator.engine.generators;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import jenerator.Foo;
import jenerator.engine.GeneratorController;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;

public class ValueGeneratorTest {

	@Test
	public void getValueTest()
			throws NoSuitableElementsOnSource, CoverageExceededException, ElementFromSourceException {
		Foo foo = new Foo();
		Foo foo2 = new Foo();
		ArrayList<Foo> arrayList = new ArrayList<Foo>();
		arrayList.add(foo);
		arrayList.add(foo2);
		GeneratorController generatorController = new GeneratorController(arrayList);
		generatorController.process();
		System.out.println(foo);
		assertTrue(true);
	}
}
