package cn.fanyetu.search.binary;

import cn.fanyetu.sort.common.SortHelper;
import cn.fanyetu.sort.quick.ThreeWaysQuickSort;

/**
 * Created by zhanghaonan at 2017/10/23
 */
public class Test {

    public static void main(String[] args) {

        int n = 10000;
        Integer[] arr = SortHelper.generateRandomArray(n, 0, n);
        new ThreeWaysQuickSort().sort(arr);

        int result = new LoopBinarySearch().search(arr, 1000);
        int result2 = new RecursiveBinarySearch().search(arr, 1000);
        System.out.println(result);
        System.out.println(result2);
    }
}
