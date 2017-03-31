package cn.fanyetu.design.structure.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 使用CGLib的动态代理类
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
		System.out.println("方法执行之前");
	}
	
	public static void after(){
		System.out.println("方法执行之后");
	}
	
	public static void main(String[] args) {
		Hello proxy = CGLibProxy.getInstance().getProxy(HelloImp.class);
		proxy.say("TOM");
	}

}
