package test1;

//Java program to demonstrate lambda expressions 
//to implement a user defined functional interface. 

//A sample functional interface (An interface with 
//single abstract method 
interface FuncInterface 
{ 
 // An abstract function 
 void abstractFun(int x); 

 // A non-abstract (or default) function 
 default void normalFun() 
 { 
    System.out.println("Hello"); 
 } 
} 
interface FuncInterface2 
{ 
 // An abstract function 
 void abstractFun2(int x, int y);
 //void abstractFun3(int z);

 // A non-abstract (or default) function 
 default void normalFun2() 
 { 
    System.out.println("Hello 2"); 
 } 
} 

public class TestLambda2 {

	public static void main(String[] args) {
        // lambda expression to implement above 
        // functional interface. This interface 
        // by default implements abstractFun() 
        FuncInterface fobj = (int x)->System.out.println(2*x); 
  
        // This calls above lambda expression and prints 10. 
        fobj.abstractFun(5);
        fobj.normalFun();
        // lambda expression to implement above 
        // functional interface. This interface 
        // by default implements abstractFun() 
        FuncInterface2 fobj2 = (int x, int y)->System.out.println(x+y); 
  
        // This calls above lambda expression and prints 10. 
        fobj2.abstractFun2(5, 6);
        fobj2.normalFun2();
	}

}
