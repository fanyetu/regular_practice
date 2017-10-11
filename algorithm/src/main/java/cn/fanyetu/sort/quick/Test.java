package cn.fanyetu.sort.quick;

import cn.fanyetu.sort.common.SortHelper;
import cn.fanyetu.sort.merge.ImprovedMergeSortTopDown;

import java.util.Arrays;

/**
 * 快速排序测试
 */
public class Test {

    public static void main(String[] args) {
        int n = 1000000;

        Integer[] arr1 = SortHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);
        Integer[] arr3 = Arrays.copyOf(arr1, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr1); // 404ms
        SortHelper.testSort("NormalQuickSort", new NormalQuickSort(), arr2); // 482ms
        SortHelper.testSort("ImprovedQuickSort", new ImprovedQuickSort(), arr3); // 345ms

        System.out.println("======================");

        Integer[] arr10= SortHelper.generateNearlyOrderedArray(n, 100);
        Integer[] arr11 = Arrays.copyOf(arr10, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr10); // 108ms
        SortHelper.testSort("ImprovedQuickSort", new ImprovedQuickSort(), arr11); // 76ms

        System.out.println("======================");

        // 又有非常多重复元素的随机数组
        Integer[] arr20 = SortHelper.generateRandomArray(n, 0, 10);
        Integer[] arr21 = Arrays.copyOf(arr20, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr20); // 108ms
        SortHelper.testSort("ImprovedQuickSort", new ImprovedQuickSort(), arr21); // 76ms
    }
}
