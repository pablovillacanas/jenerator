package jenerator.validations;

import org.junit.Test;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.reader.NaturalNumberConstraints;
import jenerator.annotations.reader.NaturalNumberGeneratorReader;

public class ConstraintReaderTest {

	@NaturalNumberGenerable(maxValue = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0, unique = true))
	Integer i;

	@Test
	public void parseo() throws NoSuchFieldException, SecurityException {
		NaturalNumberGenerable annotation = getClass().getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberConstraints constraint = new NaturalNumberGeneratorReader().readValues(annotation);
		assert (constraint.getMaxValue() == 12);
		assert (constraint.getMinValue() == Long.MIN_VALUE);
		assert (constraint.getCommonConstraints().getNullable() == 0);
		assert (constraint.getCommonConstraints().getUnique() == true);
		assert (constraint.getCommonConstraints().getSource().equals("hola"));
	}
}
