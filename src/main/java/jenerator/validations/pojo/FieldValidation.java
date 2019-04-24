package jenerator.validations.pojo;

import static jenerator.validations.pojo.MethodValidation.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import jenerator.validations.pojo.exceptions.FieldValidationException;

public class FieldValidation {



	public static boolean hasImposibleConstraints() {
		return false;
	}
}
