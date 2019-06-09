package jenerator;

import jenerator.annotations.Generable;

@Generable
public class A {

	private B b;

	public A() {
		super();
		// TODO Auto-generated constructor stub
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "A [b=" + b + "]";
	}

}
