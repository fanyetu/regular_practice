package cn.fanyetu.aop;

import org.springframework.stereotype.Component;

/**
 * 测试接口实现类
 * 
 * @author Administrator
 * 
 */
@Component
public class GreetingImpl implements Greeting {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name);
		
		//测试抛出增强,demo3
//		throw new RuntimeException("test throwing Advice");
	}
	
	
	//添加了两个以good开始的方法，在demo5中使用切面拦截这两个方法，而不拦截sayHello方法
	public void goodMorning(String name){
		System.out.println("good morning "+name);
	}
	
	public void goodNight(String name){
		System.out.println("good night "+name);
	}
	
	/**
	 * 测试aspectJ拦截指定注解的方法
	 * @param name
	 */
	@Tag
	public void testAnnotation(String name){
		System.out.println("AspectJ Annotation "+ name);
	}

}
