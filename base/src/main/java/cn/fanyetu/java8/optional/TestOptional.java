package cn.fanyetu.java8.optional;

import cn.fanyetu.java8.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class TestOptional {

	@Test
	public void test6() {
		// 原来排除空指针的方式
		Man man = new Man();
		String name = getGodnessName(man);
		System.out.println(name);

		// 使用Optional的方式避免空指针异常
		String name1 = getGodnessName(Optional.empty());
		System.out.println(name1);

		String name2 = getGodnessName(Optional.of(new NewMan()));
		System.out.println(name2);

		String name3 = getGodnessName(Optional
				.of(new NewMan(Optional.of(new Godness("具体")))));
		System.out.println(name3);
	}

	private String getGodnessName(Optional<NewMan> man){
		return man.orElse(new NewMan())
				.getGodness()
				.orElse(new Godness("默认"))
				.getName();
	}

	private String getGodnessName(Man man) {
		// 通过if判断来避免空指针异常
		if (null != man) {
			Godness godness = man.getGodness();
			if (godness != null) {
				return godness.getName();
			}
		}
		return "默认";
	}

    /*
	- Optional.of(T t)：创建一个Optional的实例
    - Optional.empty()：创建一个空的Optionla实例
    - Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
    - isPresent()：判断是否包含值
    - orElse(T t)：如果调用的Optional包含值，返回该值，否则返回t
    - orElseGet(Supplier s)：如果调用的Optional包含值，返回该值，否则返回s获取的值
    - map(Function f)：如果Optional中有值，则对其进行处理并返回处理后的Optional，否则返回Optional.empty()
    - flagMap(Function mapper)：与map类似，只是Function的返回值要求也是Optional类
     */

	@Test
	public void test5() {
		Optional<Employee> op = Optional.of(new Employee("张三", 18, 888.0));

		Optional<String> s = op.map((e) -> e.getName());

		s.ifPresent(System.out::print);

		Optional<String> s1 = op.flatMap((e) -> Optional.of(e.getName()));

		s1.ifPresent(System.out::print);
	}

	@Test
	public void test4() {
		Optional<Object> op = Optional.ofNullable(null);

		System.out.println(op.orElse(new Employee("张三", 18, 888.0)));

		System.out.println(op.orElseGet(() -> new Employee("张三", 18, 888.0)));

		op.orElseThrow(RuntimeException::new); // 直接抛出异常
	}

	@Test
	public void test3() {
		Optional<Object> op = Optional.ofNullable(new Employee("张三", 18, 888.0));

//		if (op.isPresent()){ // 判断Optional是否有值
//			System.out.println(op.get());
//		}
		op.ifPresent(System.out::println);
	}

	@Test
	public void test2() {
		Optional<Employee> op = Optional.empty(); // 使用empty构建Optional的空实例
		System.out.println(op.get()); // 使用Optional空实例获取时会抛出NoSuchElementException

		Optional<Object> op2 = Optional.ofNullable(null); //
		// 使用ofNullable构件Optional，传入null时不会抛出空指针，会返回空Optional
	}

	@Test
	public void test1() {
//        Optional<Employee> op = Optional.of(null); //
// 使用of构建optional的时候，如果传入的是null则会抛出空指针异常，方便我们定位空出现的位置
//        System.out.println(op.get());

		Optional<Employee> op = Optional.of(new Employee());
		System.out.println(op.get());
	}
}
