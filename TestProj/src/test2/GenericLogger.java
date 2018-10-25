package test2;

import java.lang.reflect.*;

/**
 * Class GenericLogger.
 */
public class GenericLogger implements InvocationHandler {
	private Object target;

	public GenericLogger(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		System.out.println("Generic Logger Entry: Invoking " + m.getName());
		return m.invoke(target, args);
	}
}