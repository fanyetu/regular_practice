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
        Integer[] arr5 = Arrays.copyOf(arr1, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr1);
        SortHelper.testSort("ThreeWaysQuickSort", new ThreeWaysQuickSort(), arr2);
        SortHelper.testSort("GeneralHeapSort", new GeneralHeapSort(), arr3);
        SortHelper.testSort("ImprovedHeapSort", new ImprovedHeapSort(), arr4);
        SortHelper.testSort("OptimalHeapSort", new OptimalHeapSort(), arr5);

        System.out.println("======================");

        Integer[] arr10 = SortHelper.generateNearlyOrderedArray(n, 100);
        Integer[] arr11 = Arrays.copyOf(arr10, n);
        Integer[] arr12 = Arrays.copyOf(arr10, n);
        Integer[] arr13 = Arrays.copyOf(arr10, n);
        Integer[] arr14 = Arrays.copyOf(arr10, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr10);
        SortHelper.testSort("ThreeWaysQuickSort", new ThreeWaysQuickSort(), arr11);
        SortHelper.testSort("GeneralHeapSort", new GeneralHeapSort(), arr12);
        SortHelper.testSort("ImprovedHeapSort", new ImprovedHeapSort(), arr13);
        SortHelper.testSort("OptimalHeapSort", new OptimalHeapSort(), arr14);

        System.out.println("======================");

        // 又有非常多重复元素的随机数组
        Integer[] arr20 = SortHelper.generateRandomArray(n, 0, 10);
        Integer[] arr21 = Arrays.copyOf(arr20, n);
        Integer[] arr22 = Arrays.copyOf(arr20, n);
        Integer[] arr23 = Arrays.copyOf(arr20, n);
        Integer[] arr24 = Arrays.copyOf(arr20, n);

        SortHelper.testSort("ImprovedMergeSortTopDown", new ImprovedMergeSortTopDown(), arr20);
        SortHelper.testSort("ThreeWaysQuickSort", new ThreeWaysQuickSort(), arr21);
        SortHelper.testSort("GeneralHeapSort", new GeneralHeapSort(), arr22);
        SortHelper.testSort("ImprovedHeapSort", new ImprovedHeapSort(), arr23);
        SortHelper.testSort("OptimalHeapSort", new OptimalHeapSort(), arr24);
    }
    /*
    ImprovedMergeSortTopDown耗时:419ms
    ThreeWaysQuickSort耗时:719ms
    GeneralHeapSort耗时:679ms
    ImprovedHeapSort耗时:613ms
    OptimalHeapSort耗时:570ms
    ======================
    ImprovedMergeSortTopDown耗时:68ms
    ThreeWaysQuickSort耗时:139ms
    GeneralHeapSort耗时:230ms
    ImprovedHeapSort耗时:184ms
    OptimalHeapSort耗时:153ms
    ======================
    ImprovedMergeSortTopDown耗时:131ms
    ThreeWaysQuickSort耗时:28ms
    GeneralHeapSort耗时:152ms
    ImprovedHeapSort耗时:164ms
    OptimalHeapSort耗时:155ms
     */
}
