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
     * 插入排序工具类，对数组中的[l...r]的元素进行排序
     *
     * @param arr
     * @param l
     * @param r
     * @param <T>
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr, int l, int r) {
//        int n = arr.length;
        // 这段代码有问题吗？问题出在哪儿？
//        for (int i = 1; i < n; i++) {
//            int minIndex = i;
//            int j;
//            for (j = i-1;j > 0 && less(arr[j],arr[minIndex]);j--){
//                minIndex = j;
//            }
//            arr[minIndex] = arr[i];
//        }

        int n = r - l + 1;
        for (int i = l + 1; i < n; i++) {
            T item = arr[i];
            int j;
            for (j = i; (j > l) && less(item, arr[j - 1]); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = item;
        }
    }

    /**
     * 生成近乎有序的数组
     *
     * @param length    数组长度
     * @param swapTimes 交换次数
     * @return
     */
    public static Integer[] generateNearlyOrderedArray(int length, int swapTimes) {

        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int posx = (int) (Math.random() * length);
            int posy = (int) (Math.random() * length);
            swap(arr, posx, posy);
        }

        return arr;
    }

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
