package jenerator.engine.generators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;

public class ElementFromDocumentGeneratorTest {

	private StringConstraints stringConstraints = new StringConstraints();
	private ElementFromDocumentGenerator elementFromDocumentGenerator;
	private int numGenerations = 6;
	private static File filetxt, filecsv;

	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		filetxt = new File(classLoader.getResource("names.txt").getFile());
		filecsv = new File(classLoader.getResource("words.csv").getFile());
		stringConstraints.setMinLenght(1);
		stringConstraints.setMaxLenght(1);
		stringConstraints.setUnique(false);
		stringConstraints.setSource("names.txt");
		stringConstraints.setNullable(0.0);
		elementFromDocumentGenerator = new ElementFromDocumentGenerator<String>(String.class, numGenerations,
				stringConstraints);
	}

	@Test
	public void testCalculateCoverage() throws CoverageExceededException, NoSuitableElementsOnSource {
		stringConstraints.setMinLenght(1);
		stringConstraints.setMaxLenght(5);
		elementFromDocumentGenerator.setQuantity(12);
		assertEquals(1.5, elementFromDocumentGenerator.calculateCoverage(), 0);

		stringConstraints.setNullable(0.5);
		elementFromDocumentGenerator.setQuantity(12);
		assertEquals(0.75, elementFromDocumentGenerator.calculateCoverage(), 0);
	}

	@Test
	public void testStringElementsNonCritical() throws CoverageExceededException, FileNotFoundException, IOException, NoSuitableElementsOnSource {
		stringConstraints.setMinLenght(5);
		stringConstraints.setMaxLenght(700);
		stringConstraints.setNullable(0.0);
		elementFromDocumentGenerator = new ElementFromDocumentGenerator<String>(String.class, numGenerations,
				stringConstraints);
		elementFromDocumentGenerator.generate();
		assertEquals(10, elementFromDocumentGenerator.getValueContainer().size());
	}

	@Test
	public void testStringElementsUniqueNonCritical()
			throws CoverageExceededException, FileNotFoundException, IOException, NoSuitableElementsOnSource {
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
