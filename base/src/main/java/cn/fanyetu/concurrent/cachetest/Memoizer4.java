package cn.fanyetu.concurrent.cachetest;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 缓存的第四种实现
 *
 * Created by Administrator on 2017/4/5.
 */
public class Memoizer4<T,E>  implements Computable<T,E>{

    //将计算保存到future中，这样如果是相同的计算，就调用future获取值，如果future没有计算完成，那么会使线程阻塞到计算完成之后再返回值的。
    private final Map<T,Future<E>> cache = new ConcurrentHashMap<T, Future<E>>();
    private final Computable<T,E> c;

    public Memoizer4(Computable<T, E> c) {
        this.c = c;
    }

    @Override
    public E compute(final T arg) throws InterruptedException {
        Future<E> f = cache.get(arg);
        if (f == null){
//            Callable<E> eval = new Callable<E>() {
//                @Override
//                public E call() throws Exception {
//                    return c.compute(arg);
//                }
//            };
            Callable<E> eval = () -> c.compute(arg);//使用lambda表达式
            FutureTask<E> ft = new FutureTask<>(eval);

            f = cache.putIfAbsent(arg, ft);//
            if (f == null){
                f = ft;
                ft.run();
            }
        }
        try {
            return f.get();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
