package cn.fanyetu.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * java堆溢出
 *
 * @author zhanghaonan
 * @date 2018/2/28
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }

    /*
    java.lang.OutOfMemoryError: Java heap space
    Dumping heap to java_pid1332.hprof ...
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at java.util.Arrays.copyOf(Arrays.java:3210)
        at java.util.Arrays.copyOf(Arrays.java:3181)
        at java.util.ArrayList.grow(ArrayList.java:261)
        at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
        at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
        at java.util.ArrayList.add(ArrayList.java:458)
        at cn.fanyetu.jvm.outofmemory.HeapOOM.main(HeapOOM.java:25)
    Heap dump file created [28605750 bytes in 0.106 secs]
     */
}
