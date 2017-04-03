package cn.fanyetu.concurrent.syncutils.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhanghaonan on 2017/4/3.
 */
public class Worker implements Runnable {

	final int id;
	final CyclicBarrier barrier;

	public Worker(int id, CyclicBarrier barrier) {
		this.id = id;
		this.barrier = barrier;
	}

	@Override
	public void run() {

		try {
			System.out.println(this.id + "starts to run !");
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println(this.id + "arrived !");

			this.barrier.await();//当线程到达时调用await方法，这个方法将阻塞直到所有线程都到达栅栏的位置
			//如果所有线程都到达的栅栏的位置，那么栅栏将打开，所有线程将会被释放，而栅栏将会被重置直到下次使用。
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}
