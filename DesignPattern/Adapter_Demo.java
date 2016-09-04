package DesignPattern;

interface target_interface {

	public void target_method();
}

interface orginal_interface_adaptee {

	public void original_method();
}

class Object_Adapter implements target_interface {
	/**
	 * Object adapter use combination with an instance of adaptee
	 * and use this instance to achieve the target interface.
	 */

	orginal_interface_adaptee original;

	public Object_Adapter(orginal_interface_adaptee original) {

		this.original = original;
	}

	@Override
	public void target_method() {
		original.original_method();
	}

}

class Class_Adapter implements target_interface, orginal_interface_adaptee {
	/**
	 * Class adapter use multiple inheritance(extends) to implement the target interface.
	 * Java not support multiple inheritance, so we use interface instead.
	 */
	@Override
	public void original_method() {
		System.out.println("execute original method");

	}

	@Override
	public void target_method() {
		System.out.println("execute target method");

	}

}

public class Adapter_Demo {

	public static void test_target(target_interface instance) {
		instance.target_method();
	}

	public static void main(String[] args) {
		
		test_target(new Object_Adapter());
		test_target(new Class_Adapter());

	}

}
