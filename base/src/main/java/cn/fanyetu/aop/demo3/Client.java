package cn.fanyetu.aop.demo3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.Greeting;

/**
 * 抛出增强测试逻辑
 * @author Administrator
 *
 */
public class Client {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/demo3/spring.xml");
		Greeting greeting = (Greeting) context.getBean("greetingProxy");
		greeting.sayHello("Tom");//在greetingImpl中抛出异常
	}
}
