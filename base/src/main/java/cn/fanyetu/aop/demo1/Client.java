package cn.fanyetu.aop.demo1;

import org.springframework.aop.framework.ProxyFactory;

import cn.fanyetu.aop.Greeting;
import cn.fanyetu.aop.GreetingAfterAdvice;
import cn.fanyetu.aop.GreetingAroundAdvice;
import cn.fanyetu.aop.GreetingBeforeAdvice;
import cn.fanyetu.aop.GreetingBeforeAndAfterAdvice;
import cn.fanyetu.aop.GreetingImpl;

/**
 * 客户端类
 * @author Administrator
 *
 */
public class Client {
	
	/*
	 * 编程式的使用方式
	 */
	public static void main(String[] args) {
		ProxyFactory proxyFactory = new ProxyFactory();//创建代理工厂(spring aop提供)
		
		proxyFactory.setTarget(new GreetingImpl());//射入的目标对象类
		
		/*
		 * 前置增强执行的顺序和添加的顺序相同，后置增强执行顺序和添加顺序相反
		 */
		proxyFactory.addAdvice(new GreetingBeforeAdvice());//添加前置增强
		proxyFactory.addAdvice(new GreetingAfterAdvice());//设置后置增强
		proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());//两个增强同时设置
		proxyFactory.addAdvice(new GreetingAroundAdvice());//设置环绕增强
		
		
		Greeting greeting = (Greeting) proxyFactory.getProxy();//获取代理类
		
		greeting.sayHello("tom");
		
		/*
		 * 输出：
			Before1
			Before2
			Before3
			Hello tom
			After3
			After2
			After1
		 */
	}

}
