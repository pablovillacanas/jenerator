package jenerator.readers;

import org.junit.Test;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.NaturalNumberConstraintsReader;
import jenerator.annotations.readers.StringConstraintsReader;

public class ConstraintReaderTest {

	@NaturalNumberGenerable(maxValue = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0, unique = true))
	Integer i;

	@StringGenerable(minLenght = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0))
	String s;

	@Test
	public void NaturalNumberGenerableParser() throws NoSuchFieldException, SecurityException {
		NaturalNumberGenerable annotation = getClass().getDeclaredField("i")
				.getAnnotation(NaturalNumberGenerable.class);
		NaturalNumberConstraints constraint = new NaturalNumberConstraintsReader().readValues(annotation);
		assert (constraint.getMaxValue() == 12);
		assert (constraint.getMinValue() == Long.MIN_VALUE);
		assert (constraint.getNullable() == 0);
		assert (constraint.getUnique() == true);
		assert (constraint.getSource().equals("hola"));
	}

	@Test
	public void StringGenerableParser() throws NoSuchFieldException, SecurityException {
		StringGenerable annotation = getClass().getDeclaredField("s").getAnnotation(StringGenerable.class);
		StringConstraints constraint = new StringConstraintsReader().readValues(annotation);
		assert (constraint.getMinLenght() == 12);
		assert (constraint.getMaxLenght() == 17);
		assert (constraint.getNullable() == 0);
		assert (constraint.getUnique() == false);
		assert (constraint.getSource().equals("hola"));
	}
}
