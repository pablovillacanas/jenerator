package jenerator.validations.congruence.checkers;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.CommonConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.utils.FieldUtils;

public class UniqueCongruenceChecker implements Predicate<Field> {

	private long maxInstancesGenerable;

	public UniqueCongruenceChecker(Long maxInstancesGenerable) {
		this.maxInstancesGenerable = maxInstancesGenerable;
	}

	@Override
	public boolean test(Field field) {
		CommonConstraints commonConstraints = FieldUtils.getConstraints(field);
		if (!commonConstraints.getSource().equals(GenerationConstraints.DEFAULTSOURCE)) {
			if (Number.class.isAssignableFrom(field.getType())) {
				NaturalNumberConstraints nnconstraints = (NaturalNumberConstraints) commonConstraints;
				long minValue = nnconstraints.getMinValue();
				long maxValue = nnconstraints.getMaxValue();
				return (maxValue - minValue) > maxInstancesGenerable;
			} else if (String.class.isAssignableFrom(field.getType())) {
				StringConstraints constraints = (StringConstraints) commonConstraints;
				constraints.getStringSimpleFormat().getCharacters().size();
			}
		} else {
			// Source element count
		}

		return false;
	}

}
