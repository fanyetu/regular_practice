package cn.fanyetu.spring.taskexecutor;

import javax.websocket.server.ServerEndpoint;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * ����ִ����
 * @author zhanghaonan
 *
 */
@Service
public class AsyncTaskService {
	
	@Async//�����÷���Ϊ�첽����
	public void executeAsyncTask(Integer i){
		System.out.println("ִ���첽����:"+i);
	}
	
	@Async
	public void executeAsyncTaskPlus(Integer i){
		System.out.println("ִ���첽����+1:"+(i+1));
	}

}
