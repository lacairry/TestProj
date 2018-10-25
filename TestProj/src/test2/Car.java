package test2;

/**
 * Class Car
 */
public class Car implements IVehicle {
	private String name;

	public Car(String name) {
		this.name = name;
	}

	public void start() {
		System.out.println("Car " + name + " started");
	}
	public void forward(){
		System.out.println("Car " + name + " forwarded");
		
	}

	public void stop(){
		System.out.println("Car " + name + " stopped");
	}
	public void reverse(){
		System.out.println("Car " + name + " reversed");
		
	}

	public String getName(){
		return this.name;
	}
}