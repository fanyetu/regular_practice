package cn.fanyetu.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 测试的前置增强类
 * 
 * 实现了spring aop提供的MethodBeforeAdvice类，将增强的代码写在里面
 * @author Administrator
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("Before1");
	}

}
