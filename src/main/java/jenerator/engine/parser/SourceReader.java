package jenerator.engine.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class SourceReader<S> implements Iterable<String> {

	protected S source;
	protected Collection<String> collection = new ArrayList<String>();

	protected abstract void parseSource(S source) throws IOException;

	public S getSource() {
		return source;
	}

	public void setSource(S source) {
		this.source = source;
	}

	public Collection<String> getCollection() {
		return collection;
	}

	public void setCollection(Collection<String> collection) {
		this.collection = collection;
	}

	@Override
	public Iterator<String> iterator() {
		return collection.iterator();
	}
}
