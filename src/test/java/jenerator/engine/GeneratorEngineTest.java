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
		List<Foo> generate = Jenerator.generate(Foo.class, 2);
		List<FooFromSource> generateFromSource = Jenerator.generate(FooFromSource.class, 2);
		List<A> hierarchicalGeneration = Jenerator.generate(A.class, 2);
		for (int i = 0; i < 2; i++) {
			System.out.println(generate.get(i));
			System.out.println(generateFromSource.get(i));
			System.out.println(hierarchicalGeneration.get(i));
		}
	}
}
