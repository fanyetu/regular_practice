package cn.fanyetu.java8.stream;

import java.util.concurrent.RecursiveTask;

/**
 * 集成RecursiveTask或者RecursiveAction实现forkJoin框架
 * RecursiveTask有返回值，RecursiveAction没有返回值
 * <p>
 * Created by zhanghaonan on 2017/10/1.
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

	private long start;
	private long end;

	private static final long THRESHOLD = 100000;// 拆分任务的临界值

	public ForkJoinCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long dur = end - start;

		// 如果小于等于临界值，就计算结果
		if (dur <= THRESHOLD) {

			long sum = 0L;

			for (long i = start; i <= end; i++) {
				sum += i;
			}

			return sum;
		} else {

			long half = (start + end) / 2;

			ForkJoinCalculate left = new ForkJoinCalculate(start, half);
			left.fork(); // 拆分任务，同时压入线程队列

			ForkJoinCalculate right = new ForkJoinCalculate(half + 1, end);
			right.fork();

			return left.join() + right.join();

		}
	}
}
