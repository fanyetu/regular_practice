package cn.fanyetu.concurrent.syncutils.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量实现的有界阻塞容器
 *
 * 在Semaphore中，acquire方法会尝试从Semaphore中获取一个许可，如果没有，就会阻塞并等待。
 * release会释放一个许可。
 *
 * Created by zhanghaonan on 2017/4/3.
 */
public class BoundedHashSet<T> {

	private final Set<T> set;
	private final Semaphore semaphore;

	/**
	 * 这里为信号量设置的大小就是有界容器的大小
	 * @param bound
	 */
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.semaphore = new Semaphore(bound);
	}

	public boolean add(T o) throws InterruptedException {
		/*
		当向容器中添加一个元素的时候，先向信号量获取一个许可，如果没有添加成果就马上释放这个许可，
		如果成功了，那么这个添加这个元素时获取的许可，只有等到有一个元素从容器中移除时才会释放，
		这样就保证了容器的有界。
		 */
		semaphore.acquire();//acquire方法将获得一个许可
		boolean wasAdded = false;
		try {
			wasAdded = set.add(o);
			return wasAdded;
		}finally {
			if (!wasAdded){
				semaphore.release();//release方法将释放许可
			}
		}
	}

	public boolean remove(Object o){
		boolean wasRemoved = set.remove(o);
		if (wasRemoved){
			semaphore.release();
		}
		return wasRemoved;
	}
}
