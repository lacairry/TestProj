package test2;
import java.util.*;

public class TestWaitNotify2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main thread: "+Thread.currentThread().getName());
		Bag bag = new Bag();
		//bag.add("Public Ball");
		Producer p1 = new Producer("P1", bag);
		Producer p2 = new Producer("P2", bag);
		Producer p3 = new Producer("P3", bag);
		Producer p4 = new Producer("P4", bag);
		Consumer c1 = new Consumer("C1", bag);
		Consumer c2 = new Consumer("C2", bag);
		Consumer c3 = new Consumer("C3", bag);
		Consumer c4 = new Consumer("C4", bag);
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();
//		try{
//			Thread.sleep(5000);
//			c1.interrupt();
//			Thread.sleep(3000);
//			c1.interrupt();
//			Thread.sleep(3000);
//			c1.interrupt();
//			Thread.sleep(3000);
//			c1.interrupt();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}

	}

}

class Producer extends Thread{
	private String name;
	private Bag bag;
	public Producer(String name, Bag bag){
		this.name = name;
		this.bag = bag;
	}
	public void run(){
		Thread.currentThread().setName(name);
		System.out.println(name+" is starting...");
		int i = 0;
		while(true){
			//System.out.println(name+" is adding a ball");
			try{
			bag.add(name+": Ball"+(++i));
			}
			catch(MyException e){
				System.out.println("Caught MyException. quitting...");
				break;
			}
			
		}
	}
}

class Consumer extends Thread{
	private String name;
	private Bag bag;
	private static int MAX = 10;
	private List<String> myBalls = new ArrayList<String>();
	public Consumer(String name, Bag bag){
		this.name = name;
		this.bag = bag;
	}
	public void run(){
		Thread.currentThread().setName(name);
		System.out.println(name+" is starting...");
		while(true){
			try{
				System.out.println(name+" got "+bag.get());
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				System.err.println(name+" interrupted: "+e);
			}
			catch(MyException e){
				System.out.println("Caught MyException. quitting...");
				break;				
			}
		}
	}
	
}

class Bag{
	private List<String> items = new ArrayList<String>();
	private static int MAX_BALLS = 3;
	
	public synchronized void add(String ball) throws MyException{
		System.out.println("Adding "+ball);
		try{
			while(items.size() >= MAX_BALLS){
				System.out.println(Thread.currentThread().getName()+": Bag is full");
				wait();
			}
			items.add(ball);
//			System.out.println("In add: bag size = "+items.size());
			notify();
//			System.out.println("*************");
			Thread.sleep(1000);
		}
		catch(InterruptedException e){
			System.err.println(Thread.currentThread().getName()+" interrupted: "+e);
			throw new MyException();
		}
		
	}
	
	
	public synchronized String get() throws MyException{
		try{
//			System.out.println("In get: bag size = "+items.size());
			while(items.isEmpty()){
				System.out.println(Thread.currentThread().getName()+": Bag is empty");
				wait();
			}
			notify();
//			System.out.println("&&&&&&&&&&&&&&&");
			return items.remove(0);
		}
		catch(InterruptedException e){
			System.err.println(Thread.currentThread().getName()+" interrupted: "+e);
			throw new MyException();
		}
	}
}

class MyException extends Exception{
	
}

