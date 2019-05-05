package jenerator.engine.generators;

import jenerator.annotations.constraints.NaturalNumberConstraints;

/**
 * El motivo de esta clase es para proporcionar a su superclase su tipo en
 * tiempo de ejecucion. La superclase será la que almacene toda la logica de
 * generacion de numeros ya que es común para todos los numeros enteros.
 * 
 * Si necesitáramos implementar nuevos comportamientos de generacion no
 * tendríamos que modificar cuatro archivos, sino uno.
 * 
 * @author pablo
 *
 */
public class IntegerGenerator extends NaturalNumberGenerator<Integer> {

	public IntegerGenerator(NaturalNumberConstraints constraints) {
		super(constraints);

	}

	@Override
	public Integer getValue() {
		return super.getValue();
	}

}
