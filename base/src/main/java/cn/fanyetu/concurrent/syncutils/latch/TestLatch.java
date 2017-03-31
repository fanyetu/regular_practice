package cn.fanyetu.concurrent.syncutils.latch;

import java.util.concurrent.CountDownLatch;

/**
 * 测试闭锁
 *
 * 闭锁的作用是可以确保某些活动直到其他活动完成之后才继续执行。
 *
 * Created by Administrator on 2017/3/31.
 */
public class TestLatch {

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        TestLatch testLatch = new TestLatch();
        try {
            long time = testLatch.timeTasks(10, task);

            System.out.println("耗时:"+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试闭锁
     * @param nThreads
     * @param task
     * @return
     * @throws InterruptedException
     */
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(nThreads);

        for (int i=0;i<nThreads;i++){
            Thread thread = new Thread("thread"+i) {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        System.out.println(name+"启动成功，开始等到开始闭锁");
                        startLatch.await();//调用开始闭锁的await方法，让所有的线程都等到准备完毕之后才继续
                        System.out.println(name + "等到闭锁成功，所有线程初始化完毕,开始执行task方法");
                        try {
                            task.run();
                        } finally {
                            System.out.println(name + "task方法执行完毕，为结束闭锁count减1");
                            endLatch.countDown();//调用结束闭锁的countDown方法，当所有线程都结束任务后主线程才结束
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();
        }

        long start = System.nanoTime();
        System.out.println("所有线程初始化完毕，主线程调用开始闭锁的countDown方法，打开所有线程");
        startLatch.countDown();//同上
        System.out.println("主线程等待所有线程任务执行");
        endLatch.await();//同上
        System.out.println("所有线程任务执行完毕");
        long end = System.nanoTime();

        return end-start;
    }
}
