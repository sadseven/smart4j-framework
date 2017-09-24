package DynamicProxyTest;

import java.lang.reflect.Proxy;


public class DynamicProxyTest {

	public static void main(String[] args) {
		Hello hello = new HelloImpl();
		DynamicProxy dynamicProxy = new DynamicProxy(hello);
		
		Hello helloProxy = dynamicProxy.getProxy();
				
		helloProxy.say("Jack");
		
	}
	
}
