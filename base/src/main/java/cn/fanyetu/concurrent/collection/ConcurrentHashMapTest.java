package cn.fanyetu.concurrent.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * ConcurrentHashMap测试
 * Created by Administrator on 2017/3/30.
 */
public class ConcurrentHashMapTest {

    private static ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<Integer, Integer>();

    public static void main(String[] args) throws InterruptedException {

        new Thread("thread1"){
            @Override
            public void run() {
                map.put(3,33);
            }
        }.start();

        new Thread("thread2"){
            @Override
            public void run() {
                map.put(4,44);
            }
        }.start();

        new Thread("thread3"){
            @Override
            public void run() {
                map.put(5,55);
            }
        }.start();

        Thread.sleep(1000);

        System.out.println(map);
    }
}
