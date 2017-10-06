package cn.fanyetu.sort.common;

import java.time.Duration;
import java.time.Instant;

/**
 * 排序相关辅助方法
 * <p>
 * create by zhanghaonan 2017/10/06
 */
public class SortHelper {

    /**
     * 测试排序所耗时间
     *
     * @param sortName
     * @param sortMethod
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void testSort(String sortName, Sort sortMethod, T[] arr) {

        Instant startTime = Instant.now();

        sortMethod.sort(arr);

        Instant endTime = Instant.now();

        if (!isSorted(arr)) {
            throw new SortException("排序失败");
        }

        Duration between = Duration.between(startTime, endTime);
        System.out.println(sortName + "耗时:" + between.toMillis() + "ms");

    }


    /**
     * 生成指定长度和范围的随机整数数组
     *
     * @param length
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int length, int rangeL, int rangeR) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            int item = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
            arr[i] = item;
        }
        return arr;
    }

    /**
     * 判断数组是否已经排序
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void printArray(T[] arr) {
        for (T anArr : arr) {
            System.out.print(anArr + " ");
        }
        System.out.println();
    }

    /**
     * 判断前一个元素是否小于后一个元素
     *
     * @param v
     * @param w
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换数组中两个元素的位置
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
