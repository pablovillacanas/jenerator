package jenerator.validations.congruence.checkers;

import jenerator.validations.congruence.exceptions.CongruenceException;

public interface CongruenceChecker<T extends Object> {

	public abstract void ckeck(T checkable) throws CongruenceException;
	
}
