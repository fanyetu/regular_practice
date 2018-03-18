package cn.fanyetu.jvm.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示jconsole 内存监控页面
 *
 * @author zhanghaonan
 * @date 2018/3/18
 */
public class JConsoleTest1 {

    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);

        Thread.sleep(10000);
    }

    public static void fillHeap(int num ) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }

        System.gc();
    }
}
