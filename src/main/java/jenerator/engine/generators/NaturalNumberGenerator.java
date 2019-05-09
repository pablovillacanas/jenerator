package jenerator.engine.generators;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.math3.random.RandomDataGenerator;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;

/**
 * <p>
 * This class return a natural number of type T within a minimum and maximun
 * values.
 * </p>
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
}
