package jenerator.engine.generators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.parser.ElementFromSourceException;

public class ElementFromDocumentGeneratorTest {

	private StringConstraints stringConstraints = new StringConstraints();
	private ElementFromDocumentGenerator<?> elementFromDocumentGenerator;
	private int numGenerations = 6;

	@Before
	public void setUp() throws Exception {
		stringConstraints.setMinLenght(1);
		stringConstraints.setMaxLenght(1);
		stringConstraints.setUnique(false);
		stringConstraints.setSource("names.txt");
		stringConstraints.setNullable(0.0);
		elementFromDocumentGenerator = new ElementFromDocumentGenerator<String>(String.class, numGenerations,
				stringConstraints);
	}

	@Test
	public void testCalculateCoverage() throws CoverageExceededException, ElementFromSourceException {
		stringConstraints.setMinLenght(1);
		stringConstraints.setMaxLenght(5);
		elementFromDocumentGenerator.setQuantity(12);
		assertEquals(1.5, elementFromDocumentGenerator.calculateCoverage(), 0);

		stringConstraints.setNullable(0.5);
		elementFromDocumentGenerator.setQuantity(12);
		assertEquals(0.75, elementFromDocumentGenerator.calculateCoverage(), 0);
	}

	@Test
	public void testStringElementsNonCritical() throws ElementFromSourceException {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(700);
		stringConstraints.setNullable(0.0);
		elementFromDocumentGenerator = new ElementFromDocumentGenerator<String>(String.class, numGenerations,
				stringConstraints);
		elementFromDocumentGenerator.generate();
		assertEquals(10, elementFromDocumentGenerator.getValueContainer().size());
	}

	@Test
	public void testStringElementsUniqueNonCritical() throws ElementFromSourceException {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(700);
		stringConstraints.setUnique(true);
		stringConstraints.setNullable(0.0);
		elementFromDocumentGenerator = new ElementFromDocumentGenerator<String>(String.class, numGenerations,
				stringConstraints);
		elementFromDocumentGenerator.generate();
		// Pepe lenght < 5
		assertEquals(5, elementFromDocumentGenerator.getValueContainer().size());
	}
}
