package DynamicProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler{
	
	private Object target;
	
	public DynamicProxy(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return  (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	private void after() {
		// TODO Auto-generated method stub
		
	}

	private void before() {
		// TODO Auto-generated method stub
		
	}

}
