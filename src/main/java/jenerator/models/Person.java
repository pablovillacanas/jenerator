/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jenerator.models;

import jenerator.annotations.Generable;

@Generable
public class Person {

	private String nombre;

	private int edad;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}
