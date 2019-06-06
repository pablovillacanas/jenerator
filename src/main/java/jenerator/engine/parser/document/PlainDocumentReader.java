package jenerator.engine.parser.document;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.engine.parser.SourceReader;

public class PlainDocumentReader extends SourceReader<PlainDocument> {

	private PlainDocument source;

	public PlainDocumentReader(PlainDocument source) throws ElementFromSourceException {
		collection = new ArrayList<String>();
		try {
			parseSource(source);
		} catch (IOException e) {
			throw new ElementFromSourceException(e);
		}
	}

	@Override
	public void parseSource(PlainDocument source) throws IOException {
		BufferedInputStream bais = new BufferedInputStream(source.getInputStream());
		int nextByte;
		String element = "";
		readloop: while ((nextByte = bais.read()) != -1) {
			checkloop: for (int i = 0; i < source.getSeparator().length; i++) {
				bais.mark(source.getSeparator().length);
				if (nextByte == source.getSeparator()[i]) {
					nextByte = bais.read();
					continue checkloop;
				} else {
					element += (char) nextByte;
					continue readloop;
				}
			}
			bais.reset();
			collection.add(element);
			element = "";
		}
		// Add last element
		collection.add(element);
	}

	public PlainDocument getSource() {
		return source;
	}

	public void setSource(PlainDocument source) {
		this.source = source;
	}

}
