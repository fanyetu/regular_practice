package cn.fanyetu.java8.stream;

import cn.fanyetu.java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * Created by Administrator on 2017/9/11.
 */
public class TestStream3 {

    /**
     * collect收集
     */
    @Test
    public void test4(){
        List<String> collect = list.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println("=======================");

        Set<String> collect1 = list.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        collect1.forEach(System.out::println);

        System.out.println("==================");

        HashSet<String> collect2 = list.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        collect2.forEach(System.out::println);
    }

    /**
     * reduce规约
     */
    @Test
    public void test3(){
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer result = arr.stream()
                .reduce(0, (x, y) -> x + y); // 有起始值

        System.out.println(result);

        System.out.println("====================");

        Optional<Double> result2 = list.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(result2.get());

        System.out.println("====================");
    }

    List<Employee> list = Arrays.asList(
            new Employee("张三", 20, 3333.33, Employee.Status.FREE),
            new Employee("李四", 15, 5555.55, Employee.Status.BUSY),
            new Employee("王五", 35, 6666.66,Employee.Status.VOCATION),
            new Employee("赵六", 58, 4444.44,Employee.Status.BUSY),
            new Employee("田七", 45, 8888.88,Employee.Status.FREE),
            new Employee("朱八", 42, 2222.22,Employee.Status.VOCATION)
    );

    /**
     * 查找与匹配
     * - allMatch——检查是否匹配所有元素
     * - anyMatch——检查是否至少匹配一个元素
     * - noneMatch——检查是否没有匹配所有元素
     * - findFirst——返回第一个元素
     * - findAny——返回当前流中的任意元素
     * - count——返回流中元素的总个数
     * - max——返回流中最大值
     * - min——返回流中最小值
     */
    @Test
    public void test1() {

        boolean allMatch = list.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(allMatch);

        System.out.println("=================");

        boolean anyMatch = list.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(anyMatch);

        System.out.println("=================");

        boolean noneMatch = list.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.FREE));

        System.out.println(noneMatch);

        System.out.println("==================");

        Optional<Employee> first = list.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();

        System.out.println(first.get());

        System.out.println("===============");

        Optional<Employee> any = list.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();

        System.out.println(any.get());

        System.out.println("==================");

    }

    @Test
    public void test2(){
        long count = list.stream()
                .count();

        System.out.println(count);

        System.out.println("==================");

        Optional<Double> min = list.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }
}
