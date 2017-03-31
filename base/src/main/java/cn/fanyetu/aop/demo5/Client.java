package cn.fanyetu.aop.demo5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.GreetingImpl;

/**
 * 切面测试客户端
 * @author Administrator
 *
 */
public class Client {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/demo5/spring.xml");
		GreetingImpl greeting = (GreetingImpl)context.getBean("greetingProxy");//将bean转型为目标类，而不是其实现的greeting接口
		
		greeting.sayHello("Tom");
		
		System.out.println("-----------------");
		
		greeting.goodMorning("Bob");
		
		System.out.println("-----------------");
		
		greeting.goodNight("Jack");
		
		/*
		 * 输出(只拦截了good开头的方法  ):
		 *  Hello Tom
			-----------------
			Before3
			good morning Bob
			After3
			-----------------
			Before3
			good night Jack
			After3
		 */
	}

}
