package DynamicProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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

	private void after() {
		// TODO Auto-generated method stub
		
	}

	private void before() {
		// TODO Auto-generated method stub
		
	}

}
