package cn.fanyetu.design.structure.proxy;
/**
 * 代理模式(静态代理)
 * @author user
 *
 */
public class HelloProxy implements Hello{

	private Hello hello;
	
	public HelloProxy(){
		hello = new HelloImp();
	}
	
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}
	
	public static void before(){
		System.out.println("Before");
	}
	
	public static void after(){
		System.out.println("After");
	}
	
	
	public static void main(String[] args) {
		HelloProxy helloProxy = new HelloProxy();
		helloProxy.say("Tom");
	}

}
