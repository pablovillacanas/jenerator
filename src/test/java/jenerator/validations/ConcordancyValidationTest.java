package jenerator.validations;

import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Test;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.validations.congruency.CongruencyChecker;
import jenerator.validations.congruency.exceptions.AnnotationMismatchFieldException;

public class ConcordancyValidationTest {

	static class Foo {
		Byte byte1;
		Short short1;
		Integer integer;
		Long long1;
		Float float1;
		Double double1;
		String object;

		static Class<?>[] retrieveByteConcordants() {
			return new Class<?>[] { NaturalNumberGenerable.class };
		}

		static Class<?>[] retrieveShortConcordants() {
			return new Class<?>[] { NaturalNumberGenerable.class };
		}

		static Class<?>[] retrieveIntegerConcordants() {
			return new Class<?>[] { NaturalNumberGenerable.class };
		}

		static Class<?>[] retrieveLongConcordants() {
			return new Class<?>[] { NaturalNumberGenerable.class };
		}

		static Class<?>[] retrieveFloatConcordants() {
			return new Class<?>[] { DecimalNumberGenerable.class };
		}

		static Class<?>[] retrieveDoubleConcordants() {
			return new Class<?>[] { DecimalNumberGenerable.class };
		}

		static Class<?>[] retrieveStringConcordants() {
			return new Class<?>[] { StringGenerable.class };
		}
	}

	@Test
	public void integerAnnotatedAsNatural() throws AnnotationMismatchFieldException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		try {
			Field[] declaredFields = Foo.class.getDeclaredFields();
			Method[] declaredMethods = Foo.class.getDeclaredMethods();
			for (int i = 0; i < declaredFields.length; i++) {
				Set<Class<?>> concordantset = CongruencyChecker.Utils
						.retrieveConcordantAnnotationsTo(declaredFields[i]);
				Class<?>[] concordantarray = concordantset.toArray(new Class<?>[concordantset.size()]);
				Class<?>[] invoke = (Class<?>[]) declaredMethods[i].invoke(new Foo());
				assertArrayEquals(invoke, concordantarray);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}