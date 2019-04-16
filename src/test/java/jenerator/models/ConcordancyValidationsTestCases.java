package jenerator.models;

import jenerator.annotations.NaturalNumberGenerable;

public class ConcordancyValidationsTestCases {

	public class StringAnnotatedAsNatural {

		@NaturalNumberGenerable
		private String foo;

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}

	}

	public class IntegerAnnotatedAsNatural {

		@NaturalNumberGenerable
		private Integer foo;

		public Integer getFoo() {
			return foo;
		}

		public void setFoo(Integer foo) {
			this.foo = foo;
		}

	}

	public class ObjectAnnotatedAsNatural {

		@NaturalNumberGenerable
		private Object foo;

		public Object getFoo() {
			return foo;
		}

		public void setFoo(Object foo) {
			this.foo = foo;
		}

	}

}
