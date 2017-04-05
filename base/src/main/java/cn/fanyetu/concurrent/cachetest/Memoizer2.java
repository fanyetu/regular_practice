package cn.fanyetu.concurrent.cachetest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 第二种缓存实现
 * 使用ConcurrentHashMap实现
 *
 * 相较于第一种缓存实现，Memoizer2可以允许多线程同时访问，但是，在同时访问时因为判断没有进行
 * 同步处理，所以有可能产生同样的值。
 * 另一点不好的就是，如果一个任务正在计算，另一个线程也要计算这个内容，但是由于这个线程不知道当前
 * 已经开始了这个计算，所有它也会进行计算，这样也浪费了计算资源。
 * Created by Administrator on 2017/4/5.
 */
public class Memoizer2<T,E> implements Computable<T,E> {

    private final Map<T,E> cache = new ConcurrentHashMap<T, E>();
    private final Computable<T,E> c;

    public Memoizer2(Computable<T, E> c) {
        this.c = c;
    }

    @Override
    public E compute(T arg) throws InterruptedException {
        E result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
