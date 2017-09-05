package cn.fanyetu.java8.lambda;

/**
 * Created by zhanghaonan on 2017/9/4.
 */
@FunctionalInterface
public interface MyFilter<T> {

	boolean test(T t);

}
