package cn.fanyetu.jvm.classload;

/**
 * 初始化阶段在执行静态方式的时，如果<clinit>()需要执行很长的时间，那么可能会造成多个线程阻塞
 *
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class DeadLoopClass {

    static {
        if (true) { // 如果不加这个，编译不会通过
            System.out.println(Thread.currentThread() + " init deadLoopClass");
            while (true) {

            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " over");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}
