package cn.fanyetu.java8.optional;

import cn.fanyetu.java8.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {

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
    public void test2() {

    }

    @Test
    public void test1() {
//        Optional<Employee> op = Optional.of(null); // 使用of构建optional的时候，如果传入的是null则会抛出空指针异常，方便我们定位空出现的位置
//        System.out.println(op.get());

        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());
    }
}
