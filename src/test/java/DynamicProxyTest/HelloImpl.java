package DynamicProxyTest;

public class HelloImpl implements Hello{

	@Override
	public void say(String str) {
		// TODO Auto-generated method stub
		System.out.println("hello " + str);
	}
	
}
