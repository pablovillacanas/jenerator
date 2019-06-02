package jenerator;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.NaturalNumberGenerable;
import jenerator.annotations.StringGenerable;

public class FooFromSource {

	@StringGenerable(constraints = @GenerationConstraints(source = "names.txt"))
	private String name;

	@NaturalNumberGenerable(constraints = @GenerationConstraints(source = "numbers.txt"))
	private Short age;

	public FooFromSource() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "FooFromSource [name=" + name + ", age=" + age + "]";
	}
}
