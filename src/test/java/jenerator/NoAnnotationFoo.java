package jenerator;

import jenerator.annotations.Generable;

@Generable
public class NoAnnotationFoo {

	Integer i;

	String s;

	Double d;

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Double getD() {
		return d;
	}

	public void setD(Double d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "NoAnnotationFoo [i=" + i + ", s=" + s + ", d=" + d + "]";
	}

}
