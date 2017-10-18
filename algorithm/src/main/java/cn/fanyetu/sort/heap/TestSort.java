package cn.fanyetu.sort.heap;

import cn.fanyetu.sort.common.SortHelper;
import cn.fanyetu.sort.merge.ImprovedMergeSortTopDown;
import cn.fanyetu.sort.quick.ThreeWaysQuickSort;

import java.util.Arrays;

/**
 * 测试堆排序
 * <p>
 * Created by zhanghaonan at 2017/10/17
 */
public class TestSort {

    public static void main(String[] args) {
        int n = 1000000;

        Integer[] arr1 = SortHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);
        Integer[] arr3 = Arrays.copyOf(arr1, n);
        Integer[] arr4 = Arrays.copyOf(arr1, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr1); // 404ms
        SortHelper.testSort("ThreeWaysQuickSort", new ThreeWaysQuickSort(), arr2); // 480ms
        SortHelper.testSort("NormalHeapSort", new NormalHeapSort(), arr3); // 659ms
        SortHelper.testSort("ImprovedHeapSort", new ImprovedHeapSort(), arr4); // 612ms

        System.out.println("======================");

        Integer[] arr10 = SortHelper.generateNearlyOrderedArray(n, 100);
        Integer[] arr11 = Arrays.copyOf(arr10, n);
        Integer[] arr12 = Arrays.copyOf(arr10, n);
        Integer[] arr13 = Arrays.copyOf(arr10, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr10); // 108ms
        SortHelper.testSort("ThreeWaysQuickSort", new ThreeWaysQuickSort(), arr11); // 144ms
        SortHelper.testSort("NormalHeapSort", new NormalHeapSort(), arr12); // 231ms
        SortHelper.testSort("ImprovedHeapSort", new ImprovedHeapSort(), arr13); // 183ms

        System.out.println("======================");

        // 又有非常多重复元素的随机数组
        Integer[] arr20 = SortHelper.generateRandomArray(n, 0, 10);
        Integer[] arr21 = Arrays.copyOf(arr20, n);
        Integer[] arr22 = Arrays.copyOf(arr20, n);
        Integer[] arr23 = Arrays.copyOf(arr20, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr20); // 1636ms
        SortHelper.testSort("ThreeWaysQuickSort", new ThreeWaysQuickSort(), arr21); // 19ms
        SortHelper.testSort("NormalHeapSort", new NormalHeapSort(), arr22); // 169ms
        SortHelper.testSort("ImprovedHeapSort", new ImprovedHeapSort(), arr23); // 161ms
    }
}
