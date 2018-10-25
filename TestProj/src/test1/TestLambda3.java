package test1;

import java.util.Comparator;
import java.util.function.Function;
import java.util.*;
import java.util.function.*;

public class TestLambda3 {
	
	public static void main(String[] args) {
		Computer com1 = new PC(1);
		Computer com2 = new Mac(2);
		Computer com3 = new Mac(3);
		List<Computer> cs = new ArrayList<>();
		cs.add(com1);
		cs.add(com2);
		cs.add(com3);
		
		Comparator c = (Object c1, Object c2) -> ((Computer)c1).getAge().compareTo(((Computer)c2).getAge());
		System.out.println("Comparator: "+c.compare(com1,com2));
		
		Comparator<Computer> cp = (Computer c1, Computer c2) -> (c1.getAge().compareTo(c2.getAge()));
		System.out.println("Comparator<Computer>: "+c.compare(com1,com2));
		
		Function<Computer, Integer> ga = Computer::getAge;
		Comparator<Computer> cpp = Comparator.comparing(Computer::getAge);		
		cpp = Comparator.comparing(ga);
		System.out.println("Comparator<Computer>: "+cpp.compare(com1,com2));
		//cpp = Comparator.comparing((Computer c1, Computer c2) -> (c1.getAge().compareTo(c2.getAge())));
		cpp = Comparator.comparing((Computer c1) ->c1.getAge());
		System.out.println("Comparator<Computer>: "+cpp.compare(com1,com2));
		Function<Computer, Integer> gag = (Computer c1) ->c1.getAge();
		cpp = Comparator.comparing(gag);
		System.out.println("Comparator<Computer>: "+cpp.compare(com1,com2));
		Function<Computer, Integer> gag1 = c10 ->c10.getAge();
		cpp = Comparator.comparing(gag1);
		System.out.println("Comparator<Computer>: "+cpp.compare(com1,com2));

		Integer computerAge = ga.apply(com1);
		//computerAge = (Computer::getAge).apply(com1);
		
		System.out.println("Computer 1 age: "+ga.apply(com1));

		Function<Computer, Integer> ga2 = com->com.getAge();
		computerAge = ga2.apply(com2);

		System.out.println("Computer 2 age: "+ga2.apply(com2));
		
		Function<Computer, Integer> ga3 = com->{com.getAge();return 4;};
		computerAge = ga3.apply(com3);

		System.out.println("Computer 3 age: "+ga3.apply(com3));
		
		cs.forEach(MyUtil::addAge);
		cs.forEach(System.out::println);
		cs.forEach(Computer::turnMeOn);
		cs.forEach(Device::turnMeOn);
		//cs.forEach(Computer::copyAge);
		//cs.forEach(Computer::showName);
		cs.forEach(n->n.shutMeDown());
		Consumer<Computer> csm = n->n.shutMeDown(); 
		cs.forEach(csm);
		Consumer<Computer> fc = Computer::turnMeOn;
		cs.forEach(fc);
		Consumer<Computer> pr = System.out::println;
		cs.forEach(pr);
		
		myForEach(cs, Computer::turnMeOn);
		com1.setAge1(100);
		
		BiFunction<Double,Double,Double> bf = Computer::calculate;
		System.out.println(bf.apply(1.5, 2.5));
		
		MyInterface abc = PC::new;
		Computer newP = abc.a();
		
		Function<Integer, PC> ff = PC::new;
		Computer newP2 = ff.apply(20);
		
		BiFunction<Integer, String, PC> fff = PC::new;
		Computer newP3 = fff.apply(2, "New Name");
		
		Function<Integer, Computer[]> ic = Computer[]::new;
		Computer[] cs2 = ic.apply(4);
		cs2[0] = ff.apply(20);
		
		
	}
	
	void calculate() {
		BiFunction<Double,Double,Double> bf = Computer::calculate;
		
	}

	static void myForEach(List<Computer> list, Consumer<Computer> c) {
		list.forEach(c);
	}
	
}

interface MyInterface{
	PC a();
}

class MyUtil{
	static void addAge(Computer c) {
		System.out.println("Adding age to computer "+c.getAge());
		c.setAge(c.getAge()+1);
	}
}


class PC extends Computer{
	PC(){}
	PC(Integer age){
		this.age = age;
	}
	PC(Integer age, String name){
		this.age = age;
		this.name = name;
	}
	@Override
	public void turnMeOn() {
		//Consumer<Computer> fun = super::turnMeOn;
		System.out.println("Turning on PC");
	}
	@Override
	public void shutMeDown() {
		System.out.println("Shutting down PC");
	}
	
	public String toString() {
		return "PC";
	}
	
	@Override
	public void setAge1(Integer age) {
		Consumer<Integer> c = super::setAge1;
		c.accept(age);
	}
	
	@Override
	public Double calculate(Double d) {
		Function<Double,Double> fun = super::calculate;
		return fun.apply(d);
	}
	
}

class Mac extends Computer{
	Mac(Integer age){
		this.age = age;
	}
	@Override
	public void turnMeOn() {
		System.out.println("Turning on Mac");
	}
	@Override
	public void shutMeDown() {
		System.out.println("Shutting down Mac");
	}
	public String toString() {
		return "Mac";
	}
	
	String name;
	
}

interface Device{
	void shutMeDown();
	void turnMeOn();
	default void log() {
		System.out.println("Logging");
	}
}

abstract class Computer implements Device{
	Computer(){}
	Computer(Integer age){
		this.age = age;
	}
	String name;
	

	@Override
	public void turnMeOn() {
		System.out.println("Turning on Computer");
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge1(Integer age) {
		System.out.println("Computer setting age: "+age);
	}
	
	public void copyAge(Computer c) {
		this.age += c.getAge();
	}
	
	Double calculate(Double d) {
		return age * d;
	}
	
	static Double calculate(Double d1, Double d2) {
		return d1 + d2;
	}

	int age;
	
	@Override
	public String toString() {
		return Integer.toString(age);
	}
	
}