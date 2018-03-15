package cn.fanyetu.jvm.allocation;

/**
 * @author zhanghaonan
 * @date 2018/3/15
 */
public class MinorGC {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        testAllocation();
    }

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    private static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        allocation4 = new byte[4 * _1M]; // 出现一次gc
    }
}
