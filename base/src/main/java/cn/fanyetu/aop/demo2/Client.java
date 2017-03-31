package cn.fanyetu.aop.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.Greeting;

/**
 * 调用aop客户端
 * 
 * 声明式的spring aop调用方式
 * @author Administrator
 *
 */
public class Client {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/demo2/spring.xml");//获取spring context
		Greeting greeting = (Greeting) context.getBean("greetingProxy");
		greeting.sayHello("Bob");
		
		/*
		 * 输出:
			Before3
			Hello Bob
			After3
		 */
		
	}

}
