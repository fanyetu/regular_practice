package cn.fanyetu.java8.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用
 * <p>
 * Created by zhanghaonan on 2017/9/6.
 */
public class TestMethodRef {

	//数组引用
	@Test
	public void test7(){
		Function<Integer,String[]> fun = (x)->new String[x];
		String[] arr = fun.apply(10);
		System.out.println(arr.length);

		Function<Integer,String[]> fun1 = String[]::new;
		String[] arr2 = fun1.apply(20);
		System.out.println(arr2.length);
	}

	// 构造器引用
	@Test
	public void test6(){
		Function<Integer,Employee> fun = (x)->new Employee(x);

		Function<Integer,Employee> fun1 = Employee::new;

		BiFunction<Integer,String,Employee> fun2 = Employee::new;
	}

	// 构造器引用
	@Test
	public void test5(){
		Supplier<Employee> sup = () -> new Employee();

		Supplier<Employee> sup2 = Employee::new;

		System.out.println(sup2.get());
	}

	//类名::实例方法名
	@Test
	public void test4() {
		BiPredicate<String, String> pre = (x, y) -> x.equals(y);

		BiPredicate<String,String> pre1 = String::equals;
	}

	// 类名::静态方法名
	@Test
	public void test3() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

		Comparator<Integer> com2 = Integer::compare;
	}

	@Test
	public void test2() {
		Employee emp = new Employee("张三", 16, 5000.00);
		Supplier<String> sup = () -> emp.getName();

		Supplier<Integer> sup1 = emp::getAge;
	}

	// 对象::实例方法名
	@Test
	public void test1() {
		Consumer<String> con = (x) -> System.out.println(x);

		PrintStream out = System.out;
		Consumer<String> con1 = out::println;

		Consumer<String> con2 = System.out::println;
	}
}
