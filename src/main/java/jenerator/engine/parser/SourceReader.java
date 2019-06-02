package jenerator.engine.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class SourceReader<S> implements Iterable<String> {

	protected Source source;
	protected Collection<String> collection = new ArrayList<String>();

	protected abstract void parseSource(S source) throws IOException;

	public abstract long getNumberOfItems();

	@Override
	public Iterator<String> iterator() {
		return collection.iterator();
	}
}
