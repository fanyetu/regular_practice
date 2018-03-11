package cn.fanyetu.jvm.outofmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用unsafe分配本机内存
 * <p>
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author zhanghaonan
 * @date 2018/3/11
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    /*
    Exception in thread "main" java.lang.OutOfMemoryError
        at sun.misc.Unsafe.allocateMemory(Native Method)
        at cn.fanyetu.jvm.outofmemory.DirectMemoryOOM.main(DirectMemoryOOM.java:24)
     */
}
