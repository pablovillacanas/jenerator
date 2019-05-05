package jenerator;

import org.junit.Test;

public class Experiments {

	@Test
	public void test() {
		int position = 0;
		for (char c = 0; c < 290; c++, position++) {
			System.out.println(c + " --> is digit? " + Character.isDigit(c) + " position: " + position);
		}
	}
	
	@Test
	public void test2() {
		for (char c = 97; c < 123; c++) {
			System.out.print(String.format("'%s',", c));
		}
	}
}
