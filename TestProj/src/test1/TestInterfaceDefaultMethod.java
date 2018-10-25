package test1;

public class TestInterfaceDefaultMethod{
	public static void main(String[] args) {
		new MyClass().log("Test 1");
		new MyClass().log2("Test 2");
		new MyClass().isNull("Test 2");
	}
}

interface Interface1 {
	void method1(String str);
	
	default void log(String str){
		if(!isNull(str))
		System.out.println("I1 logging::"+str);
	}
	default void log2(String str){
		if(!isNull(str))
		System.out.println("I1-2 logging::"+str);
	}
	
	static boolean isNull(String str) {
		System.out.println("Interface Null Check");
		return str == null ? true : "".equals(str) ? true : false;		
	}
}

interface Interface2 {
	void method1(String str);
	void method2(String str);
	void method3(String str, String str2);
	
	default void log(String str){
		System.out.println("I2 logging::"+str);
	}
}

class MyClass implements Interface1, Interface2{
	public void method1(String str) {}
	public void method2(String str) {}
	public void method3(String str, String str2) {}
	public void log(String str) {
		System.out.println("MyClass logging::"+str);
	}
	//@Override
	public boolean isNull(String str) {
		System.out.println("MyClass Null Check");
		return str == null ? true : "".equals(str) ? true : false;		
	}

}