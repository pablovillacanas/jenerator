package jenerator.parser.document;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

public class ParserInputStream {

	private static ClassLoader classLoader;
	private static File filetxt, filecsv;

	@BeforeClass
	public static void setThings() throws URISyntaxException {
		classLoader = ParserInputStream.class.getClassLoader();
		filetxt = new File(classLoader.getResource("names.txt").getFile());
		filecsv = new File(classLoader.getResource("words.csv").getFile());
	}

	@Test
	public void testWellCount() throws IOException {
		PlainDocumentReader plainDocumentReader = new PlainDocumentReader(new PlainDocument(filetxt));
		long numberOfItems = plainDocumentReader.getNumberOfItems();
		assertEquals(6, numberOfItems);
	}

	@Test
	public void testRandomElementsPlainText() throws IOException {
//		String value = new ElementFromSourceGenerator(new PlainDocumentReader(new PlainDocument(filetxt))).getValue();
//		assertNotNull(value);
	}

	@Test
	public void testRandomElementsCsv() throws IOException {
//		String value = new ElementRandomGenerator(new PlainDocumentReader(new PlainDocument(filecsv))).getValue(null);
//		System.out.println(value);
//		assertNotNull(value);
	}
}
