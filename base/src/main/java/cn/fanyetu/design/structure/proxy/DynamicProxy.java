package cn.fanyetu.design.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * @author user
 *
 */
public class DynamicProxy implements InvocationHandler{
	
	private Object target;
	
	public DynamicProxy(Object target){
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), 
				this);
	}

	/**
	 * 实现InvocationHandler的方法
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	public static void before(){
		System.out.println("方法执行之前");
	}
	
	public static void after(){
		System.out.println("方法执行之后");
	}
	
	public static void main(String[] args) {
//		HelloImp helloImp = new HelloImp();
//		//生成动态代理类
//		DynamicProxy proxy = new DynamicProxy(helloImp);
//		
//		//通过工厂方法生成代理类
//		Hello helloProxy = (Hello) Proxy.newProxyInstance(
//				helloImp.getClass().getClassLoader(),
//				helloImp.getClass().getInterfaces(), 
//				proxy);
		
		DynamicProxy proxy = new DynamicProxy(new HelloImp());
		Hello helloProxy = proxy.getProxy();
		helloProxy.say("TOM");
		
	}

}
