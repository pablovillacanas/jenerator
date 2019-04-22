package jenerator.engine.generators;

import java.lang.reflect.Type;

import org.apache.commons.math3.random.RandomDataGenerator;

import com.google.common.reflect.TypeToken;

import jenerator.annotations.reader.NaturalNumberGeneratorReader;

/**
 * This class return a natural number of type T bounded into a minimum or
 * maximun values.
 * 
 * @author pablo
 *
 * @param <T> Java type that extends Number
 */
public abstract class NaturalNumberGenerator<T extends Number> extends FieldGenerator {

	private final Type type;
	private long minValue;
	private long maxValue;

	public NaturalNumberGenerator(NaturalNumberGeneratorReader reader) {
		super();
		@SuppressWarnings("serial")
		final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
		};
		type = typeToken.getType();
		this.minValue = reader.getMinValue();
		this.maxValue = reader.getMaxValue();
	}

	@SuppressWarnings("unchecked")
	public T getRandomValue() {
		checkIntegrityMinMaxValues(type);
		RandomDataGenerator random = new RandomDataGenerator();
		if (getSource() == null) {
			switch (type.getTypeName()) {
			case "java.lang.Long":
				return (T) Long.valueOf(random.nextLong(minValue, maxValue));
			case "java.lang.Integer":
				return (T) Integer.valueOf(random.nextInt((int) minValue, (int) maxValue));
			case "java.lang.Byte":
				return (T) Byte.valueOf((byte) random.nextInt((byte) minValue, (byte) maxValue));
			case "java.lang.Short":
				return (T) Short.valueOf((short) random.nextInt((short) minValue, (short) maxValue));
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
	private void checkIntegrityMinMaxValues(Type class1) {
		switch (class1.getTypeName()) {
		case "java.lang.Integer":
			if (minValue < Integer.MIN_VALUE)
				minValue = Integer.MIN_VALUE;
			if (maxValue > Integer.MAX_VALUE)
				maxValue = Integer.MAX_VALUE;
			break;
		case "java.lang.Byte":
			if (minValue < Byte.MIN_VALUE)
				minValue = Byte.MIN_VALUE;
			if (maxValue > Byte.MAX_VALUE)
				maxValue = Byte.MAX_VALUE;
			break;
		case "java.lang.Short":
			if (minValue < Short.MIN_VALUE)
				minValue = Short.MIN_VALUE;
			if (maxValue > Short.MAX_VALUE)
				maxValue = Short.MAX_VALUE;
			break;
		}
	}
}
