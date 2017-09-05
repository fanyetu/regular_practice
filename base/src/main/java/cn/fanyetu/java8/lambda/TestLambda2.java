package cn.fanyetu.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的基础语法
 * <p>
 * Created by zhanghaonan on 2017/9/5.
 */
public class TestLambda2 {

	@Test
	public void test3() {
		Comparator<Integer> com = (x, y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};

		System.out.println("----------------");

		Comparator<Integer> com2 = (x, y) -> Integer.compare(x, y);

		Comparator<Integer> com3 = (Integer x,Integer y) -> x.compareTo(y);
	}

	// 一个参数，无返回值
	@Test
	public void test2() {
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("测试lambda表达式");

		System.out.println("--------------------");

		// 只有一个参数，小括号可以不写
		Consumer<String> con2 = x -> System.out.println(x);
		con2.accept("测试lambda表达式");
	}

	// 无参数，无返回值
	@Test
	public void test1() {
		int num = 0; // jdk1.8之前，必须为final，jdk1.8之后默认自动加上final不需要手动书写

		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world" + num);
			}
		};
		r.run();

		System.out.println("------------------");

		Runnable r1 = () -> System.out.println("hello lambda" + num);
		r1.run();
	}
}
