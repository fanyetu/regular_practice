package cn.fanyetu.java8.stream;

import cn.fanyetu.java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 * <p>
 * Created by zhanghaonan on 2017/9/7.
 */
public class TestStream2 {

    /**
     * 排序
     * sorted(),sorted(Comparator)
     */
    @Test
    public void test3() {
        List<String> arr = Arrays.asList("bbb", "ccc", "ddd", "aaa", "fff");

        arr.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("=============");

        list.stream()
                .sorted((x, y) -> {
                    if (x.getAge().equals(y.getAge())) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return x.getAge().compareTo(y.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map,flatMap
     */
    @Test
    public void test2() {

        List<String> arr = Arrays.asList("aaa", "bbb", "ccc", "ddd", "fff");

        arr.stream().map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("=================");

        list.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("=====================");

        // 使用map实现flatMap的功能

        Stream<Stream<Character>> sm = arr.stream()
                .map(TestStream2::filterCharacter);

        sm.forEach((s) -> {
            s.forEach(System.out::println);
        });

        System.out.println("====================");

        Stream<Character> sm2 = arr.stream()
                .flatMap(TestStream2::filterCharacter); // 扁平化的map操作

        sm2.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character character : str.toCharArray()) {
            list.add(character);
        }

        return list.stream();
    }

    List<Employee> list = Arrays.asList(
            new Employee("张三", 20, 3333.33),
            new Employee("李四", 15, 5555.55),
            new Employee("王五", 35, 6666.66),
            new Employee("赵六", 58, 4444.44),
            new Employee("田七", 45, 8888.88),
            new Employee("朱八", 42, 2222.22)
    );

    /*
    筛选与切片
    filter，limit，skip,distinct
     */
    @Test
    public void test1() {

        list.stream()
                .filter((x) -> {
                    System.out.println("中间操作只有在终止操作的时候才执行");
                    return x.getAge() > 35;
                })
                .forEach(System.out::println); // 终止操作

        System.out.println("============================");

        list.stream()
                .filter((x) -> x.getAge() > 35)
                .forEach(System.out::println);

        System.out.println("===================");

        list.stream().limit(2)
                .forEach(System.out::println);

        System.out.println("=====================");

        list.stream()
                .skip(5)
                .forEach(System.out::println);

        System.out.println("====================");

        list.stream()
                .skip(10)
                .forEach(System.out::println); // skip大于长度，返回一个空流


        System.out.println("==================");

        list.stream()
                .filter((x) -> {
                    System.out.println("短路"); // 只执行筛选的
                    return x.getSalary() > 3000;
                })
                .limit(2)
                .forEach(System.out::println);

    }
}
