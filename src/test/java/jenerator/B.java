package jenerator;
import jenerator.annotations.Generable;

@Generable
public class B {
	private Integer i;

	public B() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return "B [i=" + i + "]";
	}

}