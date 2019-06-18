package jenerator.readers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import jenerator.annotations.BooleanGenerable;
import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.annotations.constraints.BooleanConstraints;
import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.annotations.readers.AnnotationParser;
import jenerator.configuration.JeneratorConfiguration;

public class ConstraintReaderTest {

	JeneratorConfiguration jeneratorConfiguration = JeneratorConfiguration.getInstance();

	@NaturalNumberGenerable(maxValue = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0, unique = true))
	Integer i;

	@StringGenerable(minLenght = 12, constraints = @GenerationConstraints(source = "hola", nullable = 0))
	String s;

	@BooleanGenerable(nullable = 0.7d, relationTrueFalse = 0.3d)
	Boolean b;

	Integer ic;

	@StringGenerable
	String sc;

	@DecimalNumberGenerable
	Double d;

//	@CollectionGenerable()
	List<String> c;

//	@CollectionGenerable(numberOfItems = -3)
	List<String> c2;

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

	@Test
	public void BooleanGenerableParser() throws NoSuchFieldException, SecurityException {
		BooleanGenerable annotation = getClass().getDeclaredField("b").getAnnotation(BooleanGenerable.class);
		BooleanConstraints constraints = (BooleanConstraints) reader.parse(annotation);
		assertEquals(0.3d, constraints.getRelationTrueFalse(), 0);
		assertEquals(0.7d, constraints.getNullable(), 0);
	}

	@Test
	public void DecimalNumberGenerableParser() throws NoSuchFieldException, SecurityException {
		DecimalNumberGenerable annotation = getClass().getDeclaredField("d")
				.getAnnotation(DecimalNumberGenerable.class);
		DecimalNumberConstraints constraints = (DecimalNumberConstraints) reader.parse(annotation);
		assertEquals(Double.MAX_VALUE, constraints.getMaxValue(), 0);
		assertEquals(Double.MIN_VALUE, constraints.getMinValue(), 0);
		assertEquals(2, constraints.getPrecision(), 0);
		assertEquals(0.0d, constraints.getNullable(), 0);
	}

//	@Test
//	public void CollectionGenerableParser() throws NoSuchFieldException, SecurityException {
//		CollectionGenerable annotation = getClass().getDeclaredField("c").getAnnotation(CollectionGenerable.class);
//		CollectionConstraints constraints = (CollectionConstraints) reader.parse(annotation);
//		assertEquals(5, constraints.getNumOfItems(), 0);
//	}
//
//	@Test
//	public void CollectionNegativeItems() throws NoSuchFieldException, SecurityException {
//		CollectionGenerable annotation = getClass().getDeclaredField("c2").getAnnotation(CollectionGenerable.class);
//		CollectionConstraints constraints = (CollectionConstraints) reader.parse(annotation);
//		assertEquals(0, constraints.getNumOfItems(), 0);
//	}
}
