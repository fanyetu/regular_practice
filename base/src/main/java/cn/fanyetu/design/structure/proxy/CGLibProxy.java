package cn.fanyetu.design.structure.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * ʹ��CGLib�Ķ�̬������
 * @author user
 *
 */
public class CGLibProxy implements MethodInterceptor{
	
	private static CGLibProxy proxy = new CGLibProxy();
	
	private CGLibProxy(){}
	
	public static CGLibProxy getInstance(){
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls){
		return (T) Enhancer.create(cls, this);
	}

	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		before();
		Object result = arg3.invokeSuper(arg0, arg2);
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
		Hello proxy = CGLibProxy.getInstance().getProxy(HelloImp.class);
		proxy.say("TOM");
	}

}
