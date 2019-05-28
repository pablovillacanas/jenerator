package jenerator.validations.congruence;

import java.util.function.Function;

import jenerator.annotations.constraints.CommonConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;

/**
 * <p>
 * This class changes the minimum and maximum values by default if there are
 * bigger or smaller than than minimum and maximum default values for that data
 * type.
 * </p>
 * 
 * @author pablo
 *
 */
public class MinMaxValuesBoundedMaintainer implements Function<Class<?>, CommonConstraints> {

	private CommonConstraints constraints;

	public MinMaxValuesBoundedMaintainer(CommonConstraints constraints) {
		super();
		this.constraints = constraints;
	}

	@Override
	public CommonConstraints apply(Class<?> class1) {
		if (class1 == Integer.class) {
			NaturalNumberConstraints integerConstraitns = ((NaturalNumberConstraints) constraints);
			if (integerConstraitns.getMinValue() < Integer.MIN_VALUE)
				integerConstraitns.setMinValue(Integer.MIN_VALUE);
			if (integerConstraitns.getMaxValue() > Integer.MAX_VALUE)
				integerConstraitns.setMaxValue(Integer.MAX_VALUE);
			return integerConstraitns;
		} else if (class1 == Byte.class) {
			NaturalNumberConstraints byteConstraints = ((NaturalNumberConstraints) constraints);
			if (byteConstraints.getMinValue() < Byte.MIN_VALUE)
				byteConstraints.setMinValue(Byte.MIN_VALUE);
			if (byteConstraints.getMaxValue() > Byte.MAX_VALUE)
				byteConstraints.setMaxValue(Byte.MAX_VALUE);
			return byteConstraints;
		} else if (class1 == Short.class) {
			NaturalNumberConstraints shortConstraints = ((NaturalNumberConstraints) constraints);
			if (shortConstraints.getMinValue() < Short.MIN_VALUE)
				shortConstraints.setMinValue(Short.MIN_VALUE);
			if (shortConstraints.getMaxValue() > Short.MAX_VALUE)
				shortConstraints.setMaxValue(Short.MAX_VALUE);
			return shortConstraints;
		}
		return constraints;
	}
}
