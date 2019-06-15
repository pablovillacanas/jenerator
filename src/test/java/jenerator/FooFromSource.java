package jenerator;

import jenerator.annotations.DecimalNumberGenerable;
import jenerator.annotations.Generable;
import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;

@Generable
public class FooFromSource {

	@StringGenerable(constraints = @GenerationConstraints(source = "names.txt"))
	private String name;

	@NaturalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Byte age;

	@NaturalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Short age2;

	@NaturalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Integer age3;

	@NaturalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Long age4;

	@DecimalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Double money;

	@DecimalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Float money2;

	public FooFromSource() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Float getMoney2() {
		return money2;
	}

	public void setMoney2(Float money2) {
		this.money2 = money2;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}

	public Short getAge2() {
		return age2;
	}

	public void setAge2(Short age2) {
		this.age2 = age2;
	}

	public Integer getAge3() {
		return age3;
	}

	public void setAge3(Integer age3) {
		this.age3 = age3;
	}

	public Long getAge4() {
		return age4;
	}

	public void setAge4(Long age4) {
		this.age4 = age4;
	}

	@Override
	public String toString() {
		return "FooFromSource [name=" + name + ", age=" + age + ", age2=" + age2 + ", age3=" + age3 + ", age4=" + age4
				+ ", money=" + money + ", money2=" + money2 + "]";
	}

}
