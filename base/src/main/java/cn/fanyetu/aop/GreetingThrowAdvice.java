package cn.fanyetu.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

/**
 * 抛出增强
 * 
 * 可以在抛出增强中统一实现系统错误的相关处理(如写日志文件等)
 * 
 * @author Administrator
 * 
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice {

	/**
	 * 抛出增强实现方法
	 * 
	 * @param method
	 * @param args
	 * @param target
	 * @param e
	 */
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception e) {
		System.out.println("---------------throw Exception-------------");
		System.out.println("Target Class : " + target.getClass().getName());
		System.out.println("Method name : " + method.getName());
		System.out.println("Exception Message : " + e.getMessage());
		System.out.println("-------------------------------------------");
	}

}
