package jenerator;

import jenerator.annotations.NaturalNumberGenerable;

public class Foo {

	@NaturalNumberGenerable
	private Long i;

	public Foo() {
	}

	public Long getI() {
		return i;
	}

	public void setI(Long i) {
		this.i = i;
	}

}