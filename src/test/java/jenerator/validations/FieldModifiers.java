package jenerator.validations;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class FieldModifiers {

	public static final String HOLA = "HOLA";
	
	@Test
	public void foo() throws NoSuchFieldException, SecurityException {
		Field declaredField = FieldModifiers.class.getDeclaredField("HOLA");
		System.out.println(Modifier.FINAL);
		System.out.println(Modifier.STATIC);
		System.out.println(Modifier.PUBLIC);
		System.out.println(Modifier.isFinal(25));
		System.out.println(declaredField.getModifiers());
	}
}
