package jenerator.engine.generators;

import java.util.ArrayList;

import org.junit.Test;

import jenerator.Foo;
import jenerator.engine.GeneratorController;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;
import jenerator.engine.parser.ElementFromSourceException;

public class ValueGeneratorTest {

	@Test
	public void getValueTest()
			throws NoSuitableElementsOnSource, CoverageExceededException, ElementFromSourceException {
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
