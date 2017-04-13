package cn.fanyetu.concurrent.future;

import java.util.concurrent.*;

/**
 * 测试Runnable，Callable，Future，FutureTask
 * <p>
 * Created by Administrator on 2017/4/12.
 */
public class RunnableFutureTest {

    /**
     * 创建一个ExecutorService
     */
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunnableDemo();
        FutureDemo();
    }


    public static void RunnableDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Demo:" + fibc(41));
            }
        }).start();
    }

    /**
     * FutureTask则是一个RunnableFuture<V>，即实现了Runnbale又实现了Futrue<V>这两个接口，
     * 另外它还可以包装Runnable(实际上会转换为Callable)和Callable
     * <V>，所以一般来讲是一个符合体了，它可以通过Thread包装来直接执行，也可以提交给ExecuteService来执行
     * ，并且还可以通过v get()返回执行结果，在线程体没有执行完成的时候，主线程一直阻塞等待，执行完则直接返回结果。
     */
    public static void FutureDemo() throws ExecutionException, InterruptedException {
        Future<?> result = executor.submit(new Runnable() {
            @Override
            public void run() {
                fibc(41);
            }
        });
        System.out.println("wait future.");
        System.out.println("future result from runnable:" + result.get());

        Future<Integer> result2 = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibc(41);
            }
        });

        System.out.println("wait future.");
        System.out.println("future result form callable:" + result2.get());

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibc(41);
            }
        });

        executor.submit(futureTask);

        System.out.println("wait future.");
        System.out.println("futureTask result:" + futureTask.get());
    }


    /**
     * 效率底下的斐波那契数列, 耗时的操作
     *
     * @param num
     * @return
     */
    static int fibc(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }

}
