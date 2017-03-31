package cn.fanyetu.design.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬������
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
	 * ʵ��InvocationHandler�ķ���
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	public static void before(){
		System.out.println("����ִ��֮ǰ");
	}
	
	public static void after(){
		System.out.println("����ִ��֮��");
	}
	
	public static void main(String[] args) {
//		HelloImp helloImp = new HelloImp();
//		//���ɶ�̬������
//		DynamicProxy proxy = new DynamicProxy(helloImp);
//		
//		//ͨ�������������ɴ�����
//		Hello helloProxy = (Hello) Proxy.newProxyInstance(
//				helloImp.getClass().getClassLoader(),
//				helloImp.getClass().getInterfaces(), 
//				proxy);
		
		DynamicProxy proxy = new DynamicProxy(new HelloImp());
		Hello helloProxy = proxy.getProxy();
		helloProxy.say("TOM");
		
	}

}
