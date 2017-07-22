package cn.fanyetu.concurrent.syncutils.readwritelock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo
 * <p>
 * 读读并行
 * <p>
 * 读写串行
 * <p>
 * 写写串行
 * <p>
 * Created by zhanghaonan on 2017/7/22.
 */
public class ReadWriteLockDemo {

	//常用的lock
	private static Lock lock = new ReentrantLock();

	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();//读锁

	private static Lock writeLock = readWriteLock.writeLock();//写锁

	private int value;

	public static void main(String[] args) {
		ReadWriteLockDemo demo = new ReadWriteLockDemo();

		Runnable readRunnable = new Runnable() {
			@Override
			public void run() {
				try {
//					Object val = demo.handleRead(lock);
					Object val = demo.handleRead(readLock);
					System.out.println(System.currentTimeMillis()+"=============="+val);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		};

		Runnable writeRunnable = new Runnable() {
			@Override
			public void run() {
				try {
//					demo.handleWrite(lock, new Random().nextInt());
					demo.handleWrite(writeLock, new Random().nextInt());
					System.out.println(System.currentTimeMillis()+"==============");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		for (int i=0;i<18;i++){
			new Thread(readRunnable).start();
		}

		for (int i=18;i<20;i++){
			new Thread(writeRunnable).start();
		}
	}

	/**
	 * 读操作
	 *
	 * @param lock
	 * @return
	 * @throws Exception
	 */
	public Object handleRead(Lock lock) throws Exception {
		try {
			lock.lock();
			Thread.sleep(1000);
			return value;
		} finally {
			lock.unlock();
		}
	}

	public void handleWrite(Lock lock, int value) throws Exception {
		try {
			lock.lock();
			Thread.sleep(1000);
			this.value = value;
		} finally {
			lock.unlock();
		}
	}
}
