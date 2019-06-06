package jenerator.readers;

import org.junit.Test;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.AnnotationParser;

public class ConstraintReaderTest {

	@NaturalNumberGenerable(maxValue = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0, unique = true))
	Integer i;

	@StringGenerable(minLenght = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0))
	String s;

	AnnotationParser reader = new AnnotationParser();

	@Test
	public void NaturalNumberGenerableParser() throws NoSuchFieldException, SecurityException {
		NaturalNumberGenerable annotation = getClass().getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberConstraints constraints = (NaturalNumberConstraints) reader.parse(annotation);
		assert (constraints.getMaxValue() == 12);
		assert (constraints.getMinValue() == Long.MIN_VALUE);
		assert (constraints.getNullable() == 0);
		assert (constraints.getUnique() == true);
		assert (constraints.getSource().equals("hola"));
	}

	@Test
	public void StringGenerableParser() throws NoSuchFieldException, SecurityException {
		StringGenerable annotation = getClass().getDeclaredField("s").getAnnotation(StringGenerable.class);
		StringConstraints constraints = (StringConstraints) reader.parse(annotation);
		assert (constraints.getMinLenght() == 12);
		assert (constraints.getMaxLenght() == 17);
		assert (constraints.getNullable() == 0);
		assert (constraints.getUnique() == false);
		assert (constraints.getSource().equals("hola"));
	}
}
