package cn.fanyetu.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * 同时实现两个接口
 * @author Administrator
 *
 */
public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		System.out.println("After2");

	}

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("Before2");
	}

}
