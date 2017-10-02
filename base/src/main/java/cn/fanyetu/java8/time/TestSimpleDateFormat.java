package cn.fanyetu.java8.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class TestSimpleDateFormat {

	@Test
	public void test1() throws ExecutionException, InterruptedException {

		ExecutorService pool = Executors.newFixedThreadPool(10);

		// 在多线程的环境下simpleDateFormat会出现线程安全问题
		// 可以使用ThreadLocal的方式进行解决

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//		Callable<Date> task = new Callable<Date>() {
//
//			@Override
//			public Date call() throws Exception {
//				return sdf.parse("20171002"); // java8不用显示的加final
//			}
//		};

		// 使用ThreadLocal解决SimpleDateFormat的线程安全问题

		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return DateFormatThreadLocal.format("20171002"); // java8不用显示的加final
			}
		};

		List<Future<Date>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<Date> result : results) {
			System.out.println(result.get());
		}

		pool.shutdown(); // 关闭线程池
	}

	// 使用新的时间API，其解决了线程安全问题
	@Test
	public void test2() throws ExecutionException, InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(10);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

		Callable<LocalDate> task = new Callable<LocalDate>() {
			@Override
			public LocalDate call() throws Exception {
				return LocalDate.parse("20171002",dtf);
			}
		};

		List<Future<LocalDate>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<LocalDate> result : results) {
			System.out.println(result.get());
		}

		pool.shutdown();
	}
}
