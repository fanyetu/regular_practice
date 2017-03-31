package cn.fanyetu.concurrent.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * �Լ�ʵ�ֵ�ThreadLocal
 * @author Administrator
 *
 * @param <T>
 */
public class MyThreadLocal<T> {
	
	//����һ���̰߳�ȫ��map��key���̣߳�value�Ƕ��������
	private Map<Thread,T> container = Collections.synchronizedMap(new HashMap<Thread,T>());
	
	/**
	 * ��ȡֵ
	 * @return
	 */
	public T get(){
		Thread thread = Thread.currentThread();
		T value = container.get(thread);
		if(value == null && !container.containsKey(thread)){
			value = initialValue();
			container.put(thread, value);
		}
		return value;
	}
	
	/**
	 * ����ֵ
	 * @param value
	 */
	public void set(T value){
		container.put(Thread.currentThread(), value);
	}
	
	/**
	 * ���ó�ʼֵ
	 * @return
	 */
	protected T initialValue() {
		return null;
	}
	
	public void remove(){
		container.remove(Thread.currentThread());
	}

}
