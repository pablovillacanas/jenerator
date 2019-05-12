package jenerator.validations.congruence.checkers;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.CommonConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.utils.FieldUtils;

public class UniqueCongruenceChecker implements Predicate<Field> {

	private long wonderedInstances;

	public UniqueCongruenceChecker(Long wonderedInstances) {
		this.wonderedInstances = wonderedInstances;
	}

	@Override
	public boolean test(Field field) {
		Double possiblilities = 0.0;
		CommonConstraints commonConstraints = FieldUtils.getConstraints(field);
		if (commonConstraints.getSource().equals(GenerationConstraints.DEFAULTSOURCE)) {
			if (Number.class.isAssignableFrom(field.getType())) {
				NaturalNumberConstraints nnconstraints = (NaturalNumberConstraints) commonConstraints;
				possiblilities = (double) nnconstraints.getMaxValue() - nnconstraints.getMinValue();
			} else if (String.class.isAssignableFrom(field.getType())) {
				StringConstraints constraints = (StringConstraints) commonConstraints;
				int size = constraints.getStringSimpleFormat().getCharacters().size();
				possiblilities = Math.pow(size, constraints.getMaxLenght())
						- Math.pow(size, constraints.getMinLenght() - 1);
			}
		} else {
			// Source element count
		}
		// Minus possible nulls
		if(commonConstraints.getNullable() < 1) {
		possiblilities /= 1 - commonConstraints.getNullable();
		}else {
			return true;
		}
		return possiblilities >= wonderedInstances;
	}

}
