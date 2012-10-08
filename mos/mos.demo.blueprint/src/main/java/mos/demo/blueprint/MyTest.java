package mos.demo.blueprint;

public class MyTest {
	String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void print() {
		System.out.println("\n\n--------\n\n" + test + "\n\n--------\n\n");
	}
}
