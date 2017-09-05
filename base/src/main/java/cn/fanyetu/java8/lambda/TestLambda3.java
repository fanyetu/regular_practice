package cn.fanyetu.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8中四大内置的核心函数式接口
 * <p>
 * Created by zhanghaonan on 2017/9/5.
 */
public class TestLambda3 {

	//Predicate<T> 断言型接口
	@Test
	public void test4(){
		List<String> list = Arrays.asList("sdf", "ddd", "dddddd", "s", "sdfsdf", "act");
		List<String> result = filterStr(list, (s) -> s.length() == 3);
		for (String str : result){
			System.out.println(str);
		}
	}

	public List<String> filterStr(List<String> list , Predicate<String> pre){
		List<String> result = new ArrayList<>();
		for (String s : list){
			if (pre.test(s)){
				result.add(s);
			}
		}
		return result;
	}

	//Function<T,R> 函数型接口
	@Test
	public void test3() {
		String str = strHandler("    测试function   ", (s) -> s.trim());
		System.out.println(str);

		String str2 = strHandler("可抵扣税款的方式", (s) -> s.substring(2, s.length() - 1));
		System.out.println(str2);
	}

	public String strHandler(String str, Function<String, String> fun) {
		return fun.apply(str);
	}

	//Supplier<T> 供给型接口
	@Test
	public void test2() {
		List<Integer> numberList = getNumberList(10, () -> (int) (Math.random() * 100));
		for (Integer i : numberList) {
			System.out.println(i);
		}
	}

	public List<Integer> getNumberList(int num, Supplier<Integer> su) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(su.get());
		}
		return list;
	}

	//Consumer<T>消费型接口
	@Test
	public void test1() {
		happy(10000, (m) -> System.out.println("消费了" + m));
	}

	public void happy(double money, Consumer<Double> con) {
		con.accept(money);
	}
}
