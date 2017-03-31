package cn.fanyetu.spring.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		
		AsyncTaskService bean = context.getBean(AsyncTaskService.class);
		
		for (int i = 0; i < 10; i++) {
			bean.executeAsyncTask(i);
			bean.executeAsyncTaskPlus(i);
//			Thread.sleep(1000);
		}
		
		context.close();
	}

}
