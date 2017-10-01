package cn.fanyetu.java8.stream;

import cn.fanyetu.java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * stream api练习
 */
public class TestStream4 {

    List<Employee> list = Arrays.asList(
            new Employee("张三", 20, 3333.33, Employee.Status.FREE),
            new Employee("李四", 15, 5555.55, Employee.Status.BUSY),
            new Employee("王五", 35, 6666.66, Employee.Status.VOCATION),
            new Employee("赵六", 58, 4444.44, Employee.Status.BUSY),
            new Employee("田七", 45, 8888.88, Employee.Status.FREE),
            new Employee("朱八", 42, 2222.22, Employee.Status.VOCATION)
    );

    /**
     * 使用map和reduce计算有多少个employee
     */
    @Test
    public void test2(){
        Optional<Integer> count = list.stream()
                .map((x) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }

    /**
     * 返回数组中每个元素的平方组成的数组
     */
    @Test
    public void test1() {
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        Arrays.stream(arr)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }
}
