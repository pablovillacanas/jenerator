package jenerator.engine;

import java.util.List;

import org.junit.Test;

import jenerator.A;
import jenerator.Foo;
import jenerator.FooFromSource;
import jenerator.Jenerator;
import jenerator.JeneratorException;

public class GeneratorEngineTest {

	@Test
	public void generate() throws JeneratorException {
		List<Foo> generate = Jenerator.generate(Foo.class, 1);
		System.out.println(generate.get(0));
		List<FooFromSource> generateFromSource = Jenerator.generate(FooFromSource.class, 1);
		System.out.println(generateFromSource.get(0));
		List<A> hierarchicalGeneration = Jenerator.generate(A.class, 2);
		System.out.println(hierarchicalGeneration.get(0));
	}
}
