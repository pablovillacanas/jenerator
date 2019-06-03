package jenerator.engine.generators;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;
import jenerator.engine.parser.document.PlainDocument;
import jenerator.engine.parser.document.PlainDocumentReader;

public class ElementFromDocumentGenerator<E extends Object> extends ElementFromSourceGenerator<E, PlainDocument> {

	private Class<E> elementType;

	public ElementFromDocumentGenerator(Class<E> elementType, long quantity, NaturalNumberConstraints constraints)
			throws FileNotFoundException, IOException {
		super(quantity, constraints, new PlainDocumentReader(new PlainDocument(constraints.getSourceAsFile())));
		this.elementType = elementType;
		this.constraints = constraints;
	}

	public ElementFromDocumentGenerator(Class<E> elementType, long quantity, DecimalNumberConstraints constraints)
			throws FileNotFoundException, IOException {
		super(quantity, constraints, new PlainDocumentReader(new PlainDocument(constraints.getSourceAsFile())));
		this.elementType = elementType;
		this.constraints = constraints;
	}

	public ElementFromDocumentGenerator(Class<E> elementType, long quantity, StringConstraints constraints)
			throws FileNotFoundException, IOException {
		super(quantity, constraints, new PlainDocumentReader(new PlainDocument(constraints.getSourceAsFile())));
		this.elementType = elementType;
		this.constraints = constraints;
	}

	// TODO Number format Exception arises
	@SuppressWarnings("unchecked")
	@Override
	protected long getPossibilities() throws NoSuitableElementsOnSource {
		// Creamos una coleccion para meter los posibles elementos
		Stream<String> stream = Streams.stream(sourceReader.iterator());
		// Que tipo de elementos estamos trabajando
		if (Number.class.isAssignableFrom(elementType)) {
			if (Long.class.isAssignableFrom(elementType) || Integer.class.isAssignableFrom(elementType)
					|| Short.class.isAssignableFrom(elementType) || Byte.class.isAssignableFrom(elementType)) {
				// Natural values
				NaturalNumberConstraints nnc = (NaturalNumberConstraints) constraints;
				List<?> numbers = null;
				if (Long.class.isAssignableFrom(elementType)) {
					numbers = stream.filter(s -> !s.isEmpty()).map(s -> {
						try {
							Long valueOf = Long.valueOf(Long.parseLong(s));
							return valueOf;
						} catch (NumberFormatException e) {
						}
						return null;
					}).filter(number -> {
						if (number != null && number <= nnc.getMaxValue() && number >= nnc.getMinValue())
							return true;
						else
							return false;
					}).collect(Collectors.toList());
				} else if (Integer.class.isAssignableFrom(elementType)) {
					numbers = stream.filter(s -> !s.isEmpty() && s != null).map(s -> {
						try {
							Integer valueOf = Integer.valueOf(Integer.parseInt(s));
							return valueOf;
						} catch (NumberFormatException e) {
						}
						return null;
					}).filter(number -> {
						if (number != null && number <= nnc.getMaxValue() && number >= nnc.getMinValue())
							return true;
						else
							return false;
					}).collect(Collectors.toList());
				} else if (Short.class.isAssignableFrom(elementType)) {
					numbers = stream.filter(s -> !s.isEmpty()).map(s -> {
						try {
							Short valueOf = Short.valueOf(Short.parseShort(s));
							return valueOf;
						} catch (NumberFormatException e) {
						}
						return null;
					}).filter(number -> {
						if (number != null && number <= nnc.getMaxValue() && number >= nnc.getMinValue())
							return true;
						else
							return false;
					}).collect(Collectors.toList());
				} else if (Byte.class.isAssignableFrom(elementType)) {
					numbers = stream.filter(s -> !s.isEmpty()).map(s -> {
						try {
							Byte valueOf = Byte.valueOf(Byte.parseByte(s));
							return valueOf;
						} catch (NumberFormatException e) {
						}
						return null;
					}).filter(number -> {
						if (number != null && number <= nnc.getMaxValue() && number >= nnc.getMinValue())
							return true;
						else
							return false;
					}).collect(Collectors.toList());
				}
				setValueContainer((Collection<E>) numbers);
			} else {
				DecimalNumberConstraints nnc = (DecimalNumberConstraints) constraints;
				setValueContainer((List<E>) stream.mapToDouble(Double::valueOf).filter(number -> {
					if (number <= nnc.getMaxValue() && number >= nnc.getMinValue())
						return true;
					else
						return false;
				}).boxed().collect(Collectors.toList()));
			}
		} else {
			StringConstraints nnc = (StringConstraints) constraints;
			setValueContainer((List<E>) stream.filter(string -> {
				if (string.length() <= nnc.getMaxLenght() && string.length() >= nnc.getMinLenght()
						&& string.length() != 0)
					return true;
				else
					return false;
			}).collect(Collectors.toList()));
		}
		// Devolvemos el tamaño de la coleccion creada
		long possibilities = 0;
		if (constraints.getUnique()) {
			setValueContainer(getValueContainer().stream().collect(Collectors.toSet()));
		} else {
			possibilities = getValueContainer().size();
		}
		return possibilities;
	}

	@Override
	public Collection<E> generate() throws CoverageExceededException, NoSuitableElementsOnSource {
		getPossibilities();
		return getValueContainer();
	}

}
