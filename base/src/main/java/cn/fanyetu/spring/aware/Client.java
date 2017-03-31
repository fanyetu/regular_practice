package cn.fanyetu.spring.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		
		AwareService bean = context.getBean(AwareService.class);
		bean.outputResult();
		
		context.close();
	}

}
