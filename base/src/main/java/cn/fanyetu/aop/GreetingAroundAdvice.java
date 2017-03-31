package cn.fanyetu.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;


/**
 * 环绕增强
 * 
 * 实现了MethodInterceptor类，该类不是由spring提供，而是aop联盟提供的
 * @author Administrator
 *
 */
@Component
public class GreetingAroundAdvice implements MethodInterceptor{

	/**
	 * 实现环绕增强的方法
	 */
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		Before();
		Object result = arg0.proceed();//调用方法
		After();
		return result;
	}
	
	private void Before(){
		System.out.println("Before3");
	}
	
	private void After(){
		System.out.println("After3");
	}

}
