package jenerator;

import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.NotGenerable;

public class Foo {

	@NaturalNumberGenerable
	private Long long1;

	@NotGenerable
	private Long long2;

	@NaturalNumberGenerable
	private Short short1;

	@NaturalNumberGenerable
	private Integer integer1;

	@NaturalNumberGenerable
	private Byte byte1;

	public Foo() {
	}

	public Long getLong1() {
		return long1;
	}

	public void setLong1(Long i) {
		this.long1 = i;
	}

	public Short getShort1() {
		return short1;
	}

	public void setShort1(Short short1) {
		this.short1 = short1;
	}

	public Integer getInteger1() {
		return integer1;
	}

	public void setInteger1(Integer integer1) {
		this.integer1 = integer1;
	}

	public Byte getByte1() {
		return byte1;
	}

	public void setByte1(Byte byte1) {
		this.byte1 = byte1;
	}

	public Long getLong2() {
		return long2;
	}

	public void setLong2(Long long2) {
		this.long2 = long2;
	}

	@Override
	public String toString() {
		return "Foo [long1=" + long1 + ", long2=" + long2 + ", short1=" + short1 + ", integer1=" + integer1 + ", byte1="
				+ byte1 + "]";
	}

}