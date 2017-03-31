package cn.fanyetu.aop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.GreetingImpl;

/**
 * Aspectj测试客户端
 * @author Administrator
 *
 */
public class Client {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/aspectj/spring.xml");
		GreetingImpl greeting = (GreetingImpl)context.getBean("greetingImpl");//直接获取该bean对象就是直接获取的代理

		greeting.sayHello("Tom");
		
		/*
		 * 输出
		 * aspectJ Before
			Hello Tom
			aspectJ After
		 */
		
		//注解方法
		greeting.testAnnotation("Bob");
		
		/*
		 * 输出：
		 * aspectJ Before
			aspectJ annotation Before
			AspectJ Annotation Bob
			aspectJ annotation After
			aspectJ After

		 */
	}

}
