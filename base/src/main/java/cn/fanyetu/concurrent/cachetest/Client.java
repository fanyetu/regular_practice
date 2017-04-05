package cn.fanyetu.concurrent.cachetest;

/**
 * Created by Administrator on 2017/4/5.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            Memoizer4<String, Integer> memoizer4 = new Memoizer4<>(new ExpensiveComputable());
            try {
                Integer compute = memoizer4.compute("1000");
                System.out.println("thread1:" + compute);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Memoizer4<String, Integer> memoizer4 = new Memoizer4<>(new ExpensiveComputable());
            try {
                Integer compute = memoizer4.compute("1000");
                System.out.println("thread2:" + compute);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
