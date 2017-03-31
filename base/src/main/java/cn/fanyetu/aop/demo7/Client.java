package cn.fanyetu.aop.demo7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.GreetingImpl;

/**
 * 自动扫描(扫描切面配置)
 * @author Administrator
 *
 */
public class Client {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/demo7/spring.xml");
		GreetingImpl greeting = (GreetingImpl)context.getBean("greetingImpl");//直接获取该bean对象就是直接获取的代理
		
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
