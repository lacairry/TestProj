package test2;

import java.lang.reflect.*;

/**
 * Class Client3. Interacts with a Car Vehicle through a dynamically generated
 * VehicleProxy.
 */
public class Client3 {
	public static void main(String[] args) {
		IVehicle c = new Car("Botar");
		ClassLoader cl = IVehicle.class.getClassLoader();
		IVehicle v = (IVehicle) Proxy.newProxyInstance(cl,
				new Class[] { IVehicle.class }, new VehicleHandler(c));
		IVehicle v1 = (IVehicle) Proxy.newProxyInstance(cl,
				new Class[] { IVehicle.class }, new GenericLogger(c));
		v1.start();
		v1.forward();
		v1.stop();
	}
}