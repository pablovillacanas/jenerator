package jenerator;

import org.junit.Test;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.reader.NaturalNumberGeneratorReader;

public class ParserTest {

	class pojo {
		@NaturalNumberGenerable(maxValue = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0, unique = true))
		Integer i;
	}

	@Test
	public void parseo() throws NoSuchFieldException, SecurityException {
		NaturalNumberGenerable annotation = pojo.class.getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberGeneratorReader s = new NaturalNumberGeneratorReader(annotation);
//		annotation.
		System.out.println(s);
	}
}
