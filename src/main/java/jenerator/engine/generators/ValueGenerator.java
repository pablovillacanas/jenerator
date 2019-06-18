package jenerator.engine.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.CommonConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.exceptions.ElementFromSourceException;
import jenerator.engine.generators.exceptions.SourceNotFoundException;
import jenerator.engine.parser.Source;
import jenerator.engine.parser.SourceReader;
import jenerator.engine.parser.SourceType;
import jenerator.engine.parser.document.PlainDocument;
import jenerator.engine.parser.document.PlainDocumentReader;

public abstract class ValueGenerator<T> {

	protected SourceReader<? extends Source> sourceReader;
	protected SourceType sourceType;
	protected static RandomDataGenerator random = new RandomDataGenerator();
	private Collection<T> valueContainer;
	protected CommonConstraints commonConstraints;

	/**
	 * <p>
	 * Number of elements the developer wants to be generated.
	 * </p>
	 */
	private long quantity;

	public static final double CRITICAL_COVERAGE = 0.75;

	public ValueGenerator(long quantity) {
		super();
		this.quantity = quantity;
	}

	public ValueGenerator(long quantity, CommonConstraints constraints) {
		this.commonConstraints = constraints;
		if (constraints == null) {
			commonConstraints = new CommonConstraints();
		}
		this.quantity = quantity;
	}

	protected void initSourceReader() throws SourceNotFoundException, ElementFromSourceException {
		// TODO the logic SourceReaderFactory? By now all are Plain Document
		sourceReader = new PlainDocumentReader(new PlainDocument(commonConstraints.getSourceAsFile()));
	}

	/**
	 * <p>
	 * Calculates the coverage of values desired by the constraints. Must be
	 * override due to different kinds of calculations that have to be implemented
	 * in each generator type. The calculation takes the quantity of instances that
	 * developer want to generate and gets the relation with the possibilities that
	 * constraints allows to.
	 * </p>
	 * <p>
	 * 
	 * @return A number representing the relation between the possibilities of
	 *         values to generate and the quantity of values desired.
	 * @throws CoverageExceededException if number of values exceeds the
	 *                                   possibilities for unique values in case.
	 * @throws CoverageExceededException if there are less elements that
	 *                                   possibilities developer wants to generate.
	 */
	protected double calculateCoverage() throws CoverageExceededException {
		long possibilities = getPossibilities();
		double coverage = getValuesToGenerate() / (double) possibilities;
		if (coverage > 1 && commonConstraints.getUnique()) {
			throw new CoverageExceededException(getQuantity(), (int) possibilities);
		}
		return coverage;
	}

	/**
	 * <p>
	 * Performs an operation to calculate how many elements the generator has to
	 * create subtracting the null ones settled by nullable constraint.
	 * </p>
	 * 
	 * @return the number of values that has to be generated to achieve the quantity
	 *         of values desired.
	 */
	protected long getValuesToGenerate() {
		long toGenerate = (long) (quantity - quantity * commonConstraints.getNullable());
		return toGenerate;
	}

	/**
	 * <p>
	 * Calculates the possibilities of different values where there if they must be
	 * unique.
	 * </p>
	 * 
	 * @return all the possibilities within constraints setted.
	 */
	protected abstract long getPossibilities();

	/**
	 * <p>
	 * Adds the null elements by nullable constraint definition.
	 * </p>
	 * 
	 */
	private void addNullElements() {
		long nullElements = (long) (quantity * 1 - commonConstraints.getNullable());
		for (int i = 0; i < nullElements; i++) {
			valueContainer.add(null);
		}
	}

	/**
	 * <p>
	 * Control if the container has the same number of elements the developer
	 * require.
	 * </p>
	 * 
	 * @return true if container is filled, false otherwise.
	 */
	protected boolean containerIsFilled() {
		return valueContainer.size() == quantity;
	}

	protected Collection<T> getValueContainer() {
		return valueContainer;
	}

	/**
	 * <p>
	 * Sets the value container with the null values the developer desires.
	 * </p>
	 * 
	 * @param valueContainer The container of values for this ValueGenerator.
	 */
	protected void setValueContainer(Collection<T> valueContainer) {
		this.valueContainer = valueContainer;
		if (valueContainer.isEmpty() && commonConstraints.getNullable() != 0.0)
			addNullElements();
	}

	public Collection<T> generate() throws CoverageExceededException, ElementFromSourceException {
		if (!commonConstraints.getSource().equals(GenerationConstraints.NONSOURCE)) {
			Collection<T> possibilities = generateFromSource();
			while (!containerIsFilled()) {
				T element = possibilities.stream().skip(random.nextLong(0, possibilities.size() - 1)).findFirst().get();
				valueContainer.add(element);
			}
		} else {
			if (commonConstraints.getUnique()) {
				setValueContainer(new HashSet<T>()); // Value container now may contain null values
				if (calculateCoverage() >= CRITICAL_COVERAGE) {
					List<T> allValues = loadAllValues();
					Collections.shuffle(allValues);
					valueContainer.addAll(allValues);
					List<T> subList = allValues.subList(0, (int) getValuesToGenerate());
					setValueContainer(subList);
				} else {
					valuesRandomGenerator();
				}
			} else {
				setValueContainer(new ArrayList<T>());
				valuesRandomGenerator();
			}
		}
		List<T> values = valueContainer.stream().collect(Collectors.toList());
		Collections.shuffle(values);
		return values;
	}

	/**
	 * Loads value container until it is filled
	 */
	public abstract void valuesRandomGenerator();

	/**
	 * Loads all values on value container
	 * 
	 * @return a list with all possible values.
	 */
	public abstract List<T> loadAllValues();

	// TODO generate a good documentation for this method if possible
	public abstract Collection<T> generateFromSource() throws CoverageExceededException, ElementFromSourceException;

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getQuantity() {
		return quantity;
	}
}
