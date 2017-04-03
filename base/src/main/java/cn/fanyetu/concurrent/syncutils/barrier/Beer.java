package cn.fanyetu.concurrent.syncutils.barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhanghaonan on 2017/4/3.
 */
public class Beer {

	public static void main(String[] args) {
		final int count = 5;
		/*
		CyclicBarrier支持一个可选的Runnable 参数，当线程通过栅栏时，runnable对象将被调用。
		构造函数CyclicBarrier(int parties,Runnable barrierAction)
		，当线程在CyclicBarrier对象上调用await()方法时，栅栏的计数器将增加1，当计数器为parties时，栅栏将打开。
		 */
		final CyclicBarrier barrier = new CyclicBarrier(count, () -> {
			System.out.println("drink beer !");
		});

		for (int i=0;i<count;i++){
			new Thread(new Worker(i,barrier)).start();
		}
	}
}
