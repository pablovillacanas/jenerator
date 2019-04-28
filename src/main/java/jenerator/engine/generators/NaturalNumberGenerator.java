package jenerator.engine.generators;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.reader.NaturalNumberConstraints;

/**
 * This class return a natural number of type T bounded into a minimum or
 * maximun values.
 * 
 * @author pablo
 *
 * @param <T> Java type that extends Number
 */
public abstract class NaturalNumberGenerator<T extends Number> extends ValueGenerator<T> {

	protected NaturalNumberConstraints constraints;
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public NaturalNumberGenerator(NaturalNumberConstraints naturalNumberConstraints) {
		super();
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.constraints = naturalNumberConstraints;
	}

	@SuppressWarnings("unchecked")
	public T getValue() {
		checkIntegrityMinMaxValues(type);
		RandomDataGenerator random = new RandomDataGenerator();
		if (constraints.getCommonConstraints().getSource().equals(GenerationConstraints.DEFAULTSOURCE)) {
			switch (type.getTypeName()) {
			case "java.lang.Long":
				return (T) Long.valueOf(random.nextLong(constraints.getMinValue(), constraints.getMaxValue()));
			case "java.lang.Integer":
				return (T) Integer
						.valueOf(random.nextInt((int) constraints.getMinValue(), (int) constraints.getMaxValue()));
			case "java.lang.Byte":
				return (T) Byte.valueOf(
						(byte) random.nextInt((byte) constraints.getMinValue(), (byte) constraints.getMaxValue()));
			case "java.lang.Short":
				return (T) Short.valueOf(
						(short) random.nextInt((short) constraints.getMinValue(), (short) constraints.getMaxValue()));
			}
		}
		return null;
	}

	/**
	 * This method changes de mininimum and maximum values by default if there are
	 * bigger or smaller than than minimum and maximum default values for that data
	 * type.
	 */
	// TODO log para que el developer vea que se ha cambiado su valor en tiempo de
	// ejecucion.
	protected void checkIntegrityMinMaxValues(Type class1) {
		switch (class1.getTypeName()) {
		case "java.lang.Integer":
			if (constraints.getMinValue() < Integer.MIN_VALUE)
				constraints.setMinValue(Integer.MIN_VALUE);
			if (constraints.getMaxValue() > Integer.MAX_VALUE)
				constraints.setMaxValue(Integer.MAX_VALUE);
			break;
		case "java.lang.Byte":
			if (constraints.getMinValue() < Byte.MIN_VALUE)
				constraints.setMinValue(Byte.MIN_VALUE);
			if (constraints.getMaxValue() > Byte.MAX_VALUE)
				constraints.setMaxValue(Byte.MAX_VALUE);
			break;
		case "java.lang.Short":
			if (constraints.getMinValue() < Short.MIN_VALUE)
				constraints.setMinValue(Short.MIN_VALUE);
			if (constraints.getMaxValue() > Short.MAX_VALUE)
				constraints.setMaxValue(Short.MAX_VALUE);
			break;
		}
	}
}
