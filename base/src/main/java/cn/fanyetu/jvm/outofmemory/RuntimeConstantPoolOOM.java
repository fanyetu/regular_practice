package cn.fanyetu.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * <p>
 * 运行时常量池内存溢出异常
 *
 * @author zhanghaonan
 * @date 2018/3/3
 */
public class RuntimeConstantPoolOOM {

    /*
    使用jdk1.6及之前会抛出OOM异常，jdk1.7之后会一直运行下去
     */
    public static void main(String[] args) {
        // 使用list保持着常量池的引用，避免Full GC回收常量池
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
