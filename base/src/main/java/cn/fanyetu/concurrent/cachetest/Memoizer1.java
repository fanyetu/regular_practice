package cn.fanyetu.concurrent.cachetest;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一种缓存实现
 * 直接使用HashMap实现
 *
 * 这种方式实现有一个很大的问题，就是直接将compute方法进行了同步，导致每次只能有一个线程
 * 可以执行compute方法。效率不高，如果排队的线程较多，甚至比不使用缓存的时候消耗的时间还要长
 *
 * Created by Administrator on 2017/4/5.
 */
public class Memoizer1<T,E> implements Computable<T,E>{

    private final Map<T,E> cache = new HashMap<T, E>();
    private final Computable<T,E> c;

    public Memoizer1(Computable<T, E> c) {
        this.c = c;
    }

    @Override
    public synchronized E compute(T arg) throws InterruptedException {
        E result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
