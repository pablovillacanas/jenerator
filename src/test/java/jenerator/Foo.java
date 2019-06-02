package jenerator;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.NoGenerable;
import jenerator.annotations.StringGenerable;
import jenerator.engine.generators.StringGenerator.StringSimpleFormat;

public class Foo {

	@NaturalNumberGenerable
	private Long long1;

	@StringGenerable(style = StringSimpleFormat.ONLY_DIGITS, minLenght = 8, maxLenght = 8)
	private String phone;

	@NoGenerable
	private Long long2;

	@NaturalNumberGenerable
	private Short short1;

	@NaturalNumberGenerable
	private Integer integer1;

	@NaturalNumberGenerable
	private Byte byte1;

	@DecimalNumberGenerable(minValue = 5, maxValue = 7)
	private Double double1;

	@DecimalNumberGenerable(minValue = 5, maxValue = 7, precision = 13)
	private Float float1;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getDouble1() {
		return double1;
	}

	public void setDouble1(Double double1) {
		this.double1 = double1;
	}

	public Float getFloat1() {
		return float1;
	}

	public void setFloat1(Float float1) {
		this.float1 = float1;
	}

	@Override
	public String toString() {
		return "Foo [long1=" + long1 + ", phone=" + phone + ", long2=" + long2 + ", short1=" + short1 + ", integer1="
				+ integer1 + ", byte1=" + byte1 + ", double1=" + double1 + ", float1=" + float1 + "]";
	}

}