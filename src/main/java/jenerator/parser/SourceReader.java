package jenerator.parser;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public abstract class SourceReader<T extends Source> implements Iterable<String> {

	protected Source source;
	protected Collection<String> collection;

	protected abstract void parseSource(T source) throws IOException;

	public abstract long getNumberOfItems();

	@Override
	public Iterator<String> iterator() {
		return collection.iterator();
	}
}
