package cn.fanyetu.aop.demo6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fanyetu.aop.GreetingImpl;

/**
 * 自动代理(扫面bean的名称，在xml中通过BeanNameAutoProxyCreator进行配置)
 * @author Administrator
 *
 */
public class Client {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/fanyetu/aop/demo6/spring.xml");
		GreetingImpl greeting = (GreetingImpl)context.getBean("greetingImpl");//直接获取该bean对象就是直接获取的代理
		
		greeting.sayHello("Tom");
		greeting.goodMorning("Bob");
	}

}
