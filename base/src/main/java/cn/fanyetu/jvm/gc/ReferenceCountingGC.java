package cn.fanyetu.jvm.gc;

/**
 * 引用计数算法判断对象是否可回收的缺陷
 *
 * -XX:+PrintGCDetails
 *
 * @author zhanghaonan
 * @date 2018/3/11
 */
public class ReferenceCountingGC {

    public Object instace = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的意义是多占点内存
     */
    private byte[] bigSize = new byte[10 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instace = objB;
        objB.instace = objA;

        objA = null;
        objB = null;

        // 通知gc进行回收
        System.gc();
    }

    /*
    [GC (System.gc()) [PSYoungGen: 8756K->1323K(38400K)] 8756K->1331K(125952K), 0.0019266 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [Full GC (System.gc()) [PSYoungGen: 1323K->0K(38400K)] [ParOldGen: 8K->1113K(87552K)] 1331K->1113K(125952K), [Metaspace: 3512K->3512K(1056768K)], 0.0056469 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 38400K, used 333K [0x00000000d5800000, 0x00000000d8280000, 0x0000000100000000)
      eden space 33280K, 1% used [0x00000000d5800000,0x00000000d58534a8,0x00000000d7880000)
      from space 5120K, 0% used [0x00000000d7880000,0x00000000d7880000,0x00000000d7d80000)
      to   space 5120K, 0% used [0x00000000d7d80000,0x00000000d7d80000,0x00000000d8280000)
     ParOldGen       total 87552K, used 1113K [0x0000000080800000, 0x0000000085d80000, 0x00000000d5800000)
      object space 87552K, 1% used [0x0000000080800000,0x00000000809165a8,0x0000000085d80000)
     Metaspace       used 3520K, capacity 4498K, committed 4864K, reserved 1056768K
      class space    used 387K, capacity 390K, committed 512K, reserved 1048576K
     */
}
