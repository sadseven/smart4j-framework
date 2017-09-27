package org.smart4j.framework.ThreadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {

	private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());
	
	public void set(T value) {
		Thread currentThread = Thread.currentThread();
		container.put(currentThread, value);
	}
	
	public T get() {
		Thread currentThread = Thread.currentThread();
		T value = container.get(currentThread);
		if (value == null && !container.containsKey(currentThread)) {
			value = initialValue();
			container.put(currentThread, value);
		}
		return value;
	}
	
	
	protected T initialValue() {
		return null;
	}
	
}
