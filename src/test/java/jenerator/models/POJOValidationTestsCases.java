package jenerator.models;

public class POJOValidationTestsCases {

	public class ClassWellFormed {
		private int foo;

		public int getFoo() {
			return foo;
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public class ClassWithoutSetter {
		private int foo;

		public ClassWithoutSetter() {
		}

		public int getFoo() {
			return foo;
		}
	}

	public class ClassWithoutGetter {
		@SuppressWarnings("unused")
		private int foo;

		public ClassWithoutGetter() {
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public class ClassWithoutConstructor {
		private int foo;

		public ClassWithoutConstructor(int foo) {
			this.foo = foo;
		}

		public int getFoo() {
			return foo;
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public class ClassWithoutCorrectModifierConstructor {
		private int foo;

		private ClassWithoutCorrectModifierConstructor() {
		}

		public int getFoo() {
			return foo;
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}
}
