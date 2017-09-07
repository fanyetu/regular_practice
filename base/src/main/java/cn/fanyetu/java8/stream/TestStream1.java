package cn.fanyetu.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zhanghaonan on 2017/9/7.
 */
public class TestStream1 {

	// 创建流
	@Test
	public void test1() {
		//1.collection系列集合提供的stream()方法
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();// 创建串行流
		Stream<String> stringStream = list.parallelStream();// 创建并行流

		// 2.Arrays.stream()获取数组流
		String[] strs = new String[10];
		Stream<String> stream1 = Arrays.stream(strs);

		// 3.Stream.of()创建
		Stream<String> stream2 = Stream.of("aa", "bb", "cc");

		// 4.无限流
		// Stream.iterate(起始值，一元操作)
		Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
//		stream3.forEach(System.out::println);
		stream3.limit(10).forEach(System.out::println);

		// Stream.generate
		Stream<Double> stream4 = Stream.generate(Math::random);
		stream4.limit(10).forEach(System.out::println);
	}
}
