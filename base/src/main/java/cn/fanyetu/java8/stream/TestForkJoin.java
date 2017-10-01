package cn.fanyetu.java8.stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by zhanghaonan on 2017/10/1.
 */
public class TestForkJoin {

	/**
	 * java8并行流
	 */
	@Test
	public void test3(){//18853
		long sum = LongStream.rangeClosed(0, 50000000000L)
				.parallel() // 将流切换成并行流
				.reduce(0, Long::sum);

		System.out.println("计算结果：" + sum);
	}

	/**
	 * 使用forkJoin框架进行计算
	 */
	@Test
	public void test1() {//30659
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0, 50000000000L);
		Long sum = pool.invoke(task);
		System.out.println("计算结果：" + sum);
	}

	/**
	 * 普通for循环计算
	 */
	@Test
	public void test2() {
		long sum = 0L;

		for (long i = 0; i < 50000000000L; i++) {
			sum += i;
		}

		System.out.println("计算结果：" + sum);
	}


	private Instant start;
	private Instant end;

	@Before
	public void before() {
		start = Instant.now();
	}

	@After
	public void after() {
		end = Instant.now();
		System.out.println("执行耗时:" + Duration.between(start, end).toMillis());
	}
}
