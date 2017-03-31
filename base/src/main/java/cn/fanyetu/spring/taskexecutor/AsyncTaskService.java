package cn.fanyetu.spring.taskexecutor;

import javax.websocket.server.ServerEndpoint;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 任务执行类
 * @author zhanghaonan
 *
 */
@Service
public class AsyncTaskService {
	
	@Async//表明该方法为异步方法
	public void executeAsyncTask(Integer i){
		System.out.println("执行异步任务:"+i);
	}
	
	@Async
	public void executeAsyncTaskPlus(Integer i){
		System.out.println("执行异步任务+1:"+(i+1));
	}

}
