package jenerator.validations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import jenerator.annotations.NaturalNumberGenerable;

public class FieldModifiers {

	public static final String HOLA = "HOLA";

	@Deprecated
	@NaturalNumberGenerable
	private Integer i;

	@Test
	public void foo() throws NoSuchFieldException, SecurityException {
		Field declaredField = FieldModifiers.class.getDeclaredField("HOLA");
		System.out.println(Modifier.FINAL);
		System.out.println(Modifier.STATIC);
		System.out.println(Modifier.PUBLIC);
		System.out.println(Modifier.isFinal(25));
		System.out.println(declaredField.getModifiers());
	}

	@Test
	public void baz() throws NoSuchFieldException, SecurityException {
		Field field = FieldModifiers.class.getDeclaredField("i");
		List<Annotation> collect = Arrays.asList(field.getAnnotations()).stream().filter(ann -> ann.annotationType().getCanonicalName().startsWith("jenerator")).collect(Collectors.toList());
		collect.forEach(a -> System.out.println(a.annotationType()));
		assert(collect.get(0).annotationType() == NaturalNumberGenerable.class);
		assert(collect.get(0) instanceof NaturalNumberGenerable);
	}

}
