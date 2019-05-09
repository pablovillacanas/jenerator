package jenerator.validations.congruence.checkers;

import jenerator.validations.congruence.exceptions.CongruenceException;

public abstract class CongruenceChecker<T extends Object> {

	public abstract void ckeck(T checkable) throws CongruenceException;
	
}
