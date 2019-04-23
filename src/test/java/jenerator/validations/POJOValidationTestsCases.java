package jenerator.validations;

public class POJOValidationTestsCases {

	// Why static?
	// http://thecodersbreakfast.net/index.php?post/2011/09/26/Inner-classes-and-the-myth-of-the-default-constructor
	public static class ClassWellFormedWithoutExplicitConstructor {
		private int foo;

		public int getFoo() {
			return foo;
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public static class ClassWellFormedWithExplicitConstructor {
		private int foo;

		public ClassWellFormedWithExplicitConstructor() {

		}

		public int getFoo() {
			return foo;
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public static class ClassWithoutSetter {
		private int foo;

		public ClassWithoutSetter() {
		}

		public int getFoo() {
			return foo;
		}
	}

	public static class ClassWithoutGetter {
		@SuppressWarnings("unused")
		private int foo;

		public ClassWithoutGetter() {
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public static class ClassWithoutEmptyConstructor {
		private int foo;

		public ClassWithoutEmptyConstructor(int foo) {
			this.foo = foo;
		}

		public int getFoo() {
			return foo;
		}

		public void setFoo(int foo) {
			this.foo = foo;
		}
	}

	public static class ClassWithoutCorrectModifierConstructor {
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
