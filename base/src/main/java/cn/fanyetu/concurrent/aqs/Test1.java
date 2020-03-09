package cn.fanyetu.concurrent.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhanghaonan
 * @date 2020/3/9
 */
public class Test1 {

    public static int count= 0;

    public static Lock lock = new ReentrantLock();

    public static void incr() {
        lock.lock();
        try {
            Thread.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->Test1.incr()).start();
        }

        Thread.sleep(4000);
        System.out.println("Result:" + count);
    }


}
