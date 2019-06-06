package jenerator.engine.parser.document;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.engine.generators.exceptions.SourceNotFoundException;

public class ParserInputStream {

	private static ClassLoader classLoader;
	private static File filetxt;

	@BeforeClass
	public static void setThings() throws URISyntaxException {
		classLoader = ParserInputStream.class.getClassLoader();
		filetxt = new File(classLoader.getResource("names.txt").getFile());
	}

	@Test
	public void testWellCount() throws SourceNotFoundException, ElementFromSourceException {
		PlainDocumentReader plainDocumentReader = new PlainDocumentReader(new PlainDocument(filetxt));
		long numberOfItems = plainDocumentReader.getCollection().size();
		assertEquals(13, numberOfItems);
	}
}
