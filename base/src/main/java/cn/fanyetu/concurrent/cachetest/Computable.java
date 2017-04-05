package cn.fanyetu.concurrent.cachetest;

/**
 * 测试接口
 *
 * Created by Administrator on 2017/4/5.
 */
public interface Computable<T,E> {

    E compute(T arg) throws InterruptedException;
}
