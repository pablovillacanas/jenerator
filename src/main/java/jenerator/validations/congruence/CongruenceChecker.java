package jenerator.validations.congruence;

public interface CongruenceChecker<T extends Object, U extends Object> {

	public boolean ckeck(T item1, U item2);

}
