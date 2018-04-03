package cn.fanyetu.jvm.concurrent;

/**
 * @author zhanghaonan
 * @date 2018/4/3
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase(){
        System.out.println(Thread.currentThread().getName());
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1)
            Thread.yield();

        System.out.println(race);
    }
}
