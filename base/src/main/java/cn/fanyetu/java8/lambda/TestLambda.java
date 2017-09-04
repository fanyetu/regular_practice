package cn.fanyetu.java8.lambda;

import org.junit.Test;

import javax.security.jacc.EJBMethodPermission;
import java.util.*;

/**
 * Created by zhanghaonan on 2017/9/4.
 */
public class TestLambda {

	// 使用lambda表达式
	@Test
	public void test2() {
//		Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
		Comparator<Integer> com = Comparator.comparingInt(x -> x);// ???什么鬼
		TreeSet<Integer> tree = new TreeSet<>(com);
	}

	// 原来的匿名内部类
	@Test
	public void test1() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};

		TreeSet<Integer> tree = new TreeSet<>(com);
	}

	List<Employee> list = Arrays.asList(
			new Employee("张三", 20, 3333.33),
			new Employee("李四", 15, 5555.55),
			new Employee("王五", 35, 6666.66),
			new Employee("赵六", 58, 4444.44),
			new Employee("田七", 45, 8888.88),
			new Employee("朱八", 42, 2222.22)
	);

	// 过滤集合中年龄大于30的
	@Test
	public void test3() {
		List<Employee> result = filter1(list);
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}

	public List<Employee> filter1(List<Employee> list) {
		List<Employee> result = new ArrayList<>();
		for (Employee employee : list) {
			if (employee.getAge() > 30) {
				result.add(employee);
			}
		}
		return result;
	}

	public List<Employee> filter2(List<Employee> list) {
		List<Employee> result = new ArrayList<>();
		for (Employee employee : list) {
			if (employee.getSalary() > 5000) {
				result.add(employee);
			}
		}
		return result;
	}

	// 过滤工资大于5000的
	@Test
	public void test4() {
		List<Employee> result = filter2(list);
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}

	public List<Employee> filter3(List<Employee> list, MyFilter<Employee> filter) {
		List<Employee> result = new ArrayList<>();
		for (Employee employee : list) {
			if (filter.test(employee)) {
				result.add(employee);
			}
		}
		return result;
	}

	//优化方式1：使用策略模式
	@Test
	public void test5() {
		List<Employee> result = filter3(list, new EmployeeAgeFilter());
		for (Employee employee : result) {
			System.out.println(employee);
		}

		System.out.println("----------------------------");

		result = filter3(list, new EmployeeSalaryFilter());
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}

	//优化方式2：使用匿名内部类
	@Test
	public void test6() {
		List<Employee> result = filter3(list, new MyFilter<Employee>() {
			@Override
			public boolean test(Employee employee) {
				return employee.getAge() < 30;
			}
		});
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}

	//优化方式3：使用lambda表达式
	@Test
	public void test7() {
		List<Employee> result = filter3(list, (e) -> e.getAge() > 30);
		result.forEach(System.out::println);
	}

	//优化方式4：使用stream API
	@Test
	public void test8() {
		list.stream()
				.filter((e) -> e.getAge() > 30)
				.forEach(System.out::println);

		System.out.println("---------------------------");

		list.stream()
				.map(Employee::getName)
				.limit(3)
				.sorted()
				.forEach(System.out::println);
	}

}
