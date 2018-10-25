package test1;

import java.util.stream.*;
import java.util.*;
import java.util.function.*;
import java.io.*;

public class TestStream {
	
	static Vehicle myVehicle = new Vehicle("MyVeh");

	public static void main(String[] args) {
		String[] arr = new String[]{"a", "a", "b", "c", "d", "e", "f"};
		Stream<String> stream = Arrays.stream(arr);
		stream = Stream.of("a", "b", "c");
		List<String> list = Arrays.asList(arr);
		stream = list.stream();
		list.parallelStream().forEach(System.out::println);
		list.stream().forEach(e->System.out.println("*"+e));
		
		List<String> resultList 
		  = list.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
		
		System.out.println("Distinct count: "+list.stream().distinct().count());
		System.out.println("Ccount: "+list.stream().count());
		
		boolean isExist = list.stream().anyMatch(e->e.contains("a"));
		
		Predicate<String> p = e->e.contains("a");
		if(list.stream().anyMatch(p)) {
			print("Match");
		}
		
		Stream<String> s2 = list.stream().filter(p);
		s2.forEach(System.out::println);
		
		s2 = list.stream().map(m->{return "-"+m;});
		s2.forEach(Util1::print);

		Function<String,String> f = m->{return "&"+m;};
		list.stream().map(f).forEach(Util1::print);
		
		Function<String, Vehicle> fun = Vehicle::new;
		
		Vehicle[] vs = {fun.apply("V1"),new Vehicle("V2"), new Vehicle("V3")};
		Stream<String> parts = Arrays.asList(vs).stream().flatMap(v->Arrays.asList(v.getParts()).stream().map(pp->{return v.getName()+":"+pp;}));
		parts.forEach(pt->print(pt));
		List<Vehicle> vl = Arrays.asList(vs);
		if(vl.stream().anyMatch(v->v.getName().contains("1")))
			vl.stream().filter(v->v.getName().contains("1")).forEach(pt->print(pt.getName()));
		print("All match 1: "+vl.stream().allMatch(v->v.getName().contains("1")));
		print("All match V: "+vl.stream().allMatch(v->v.getName().contains("V")));
		print("none match 1: "+vl.stream().noneMatch(v->v.getName().contains("1")));
		
		List<Vehicle> vns = vl.stream().map(v->{v.setName(v.getName().toLowerCase());return v;}).collect(Collectors.toList());
		vns.forEach(v->print(v.getName()));

		List<String> vns2 = vl.stream().map(Vehicle::getName).collect(Collectors.toList());
		vns2.forEach(s->print("^"+s));
		List<String> vns3 = vl.stream().map(v->v.getName()).collect(Collectors.toList());
		vns3.forEach(s->print(">"+s));
		
		//vns3.forEach(this::print);
		vns3.forEach(System.out::print);
		PrintStream ps = System.out;
		vns3.forEach(ps::print);
		vns3.forEach(myVehicle::print);
		
		List<Integer> integers = Arrays.asList(1, 2, 3);
		Integer reduced = integers.stream().reduce(23, (a, b) -> a - b);
		print(reduced.toString());
		Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
		print(sum.toString());
		
		new TestStream().calc();
	}
	
	static void print(String str) {
		System.out.println(str);
	}
	
	void calc() {
		String[] arr = new String[]{"a", "a", "b", "c", "d", "e", "f"};
		Stream<String> stream = Arrays.stream(arr);
		stream = Stream.of("a", "b", "c");
		List<String> list = Arrays.asList(arr);
		stream = list.stream();
		
		stream.forEach(new Vehicle("Test")::print);
		//stream.forEach(this::print);

	}
	
	

}

interface Util1{
	static void print(String str) {
		System.out.println(str);
	}
	
}

class Vehicle{
	private String name;
	Vehicle(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String[] parts = {"P1", "P2", "P3", "P4"};

	public String[] getParts() {
		return parts;
	}

	public void setParts(String[] parts) {
		this.parts = parts;
	}
	
	void print(String str) {
		System.out.println(str);
		
	}
	
}
