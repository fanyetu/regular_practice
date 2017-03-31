package cn.fanyetu.aop.demo4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.Apology;
import cn.fanyetu.aop.GreetingImpl;

/**
 * 引入增强测试客户端
 * @author Administrator
 *
 */
public class Client {
	
	/**
	 * 测试引入增强
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/demo4/spring.xml");
		GreetingImpl greeting = (GreetingImpl)context.getBean("greetingProxy");//将bean转型为目标类，而不是其实现的greeting接口
		
		greeting.sayHello("Tom");
		
		Apology apology = (Apology) greeting;//将目标类强制向上转型为Apology接口
		apology.saySorry("Bob");
	}
	
}
