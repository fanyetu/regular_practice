package cn.fanyetu.sort.merge;

import cn.fanyetu.sort.common.SortHelper;
import cn.fanyetu.sort.insertion.ImprovedInsertionSort;
import cn.fanyetu.sort.shell.ShellSort;

import java.util.Arrays;

/**
 * 测试归并排序
 */
public class Test {

    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr1 = SortHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);
        Integer[] arr3 = Arrays.copyOf(arr1, n);
        Integer[] arr4 = Arrays.copyOf(arr1, n);
        Integer[] arr5 = Arrays.copyOf(arr1, n);

        SortHelper.testSort("improvedInsertionSort", new ImprovedInsertionSort(), arr1); //10952ms
        SortHelper.testSort("shellSort", new ShellSort(), arr2); //37ms
        SortHelper.testSort("MergeSortTopDown", new MergeSortTopDown(), arr3); //66ms
        SortHelper.testSort("ImprovedMergeSortTopDown",new ImprovedMergeSortTopDown(),arr4); //31ms
        SortHelper.testSort("MergeSortBottomUp",new MergeSortBottomUp(),arr5); //25ms

        // 近乎有序数组测试
        Integer[] arr6= SortHelper.generateNearlyOrderedArray(n, 10);
        Integer[] arr7 = Arrays.copyOf(arr6, n);
        Integer[] arr8 = Arrays.copyOf(arr6, n);
        Integer[] arr9 = Arrays.copyOf(arr6, n);

        SortHelper.testSort("improvedInsertionSort",new ImprovedInsertionSort(),arr6); //4ms
        SortHelper.testSort("MergeSortTopDown",new MergeSortTopDown(),arr7); //22ms
        SortHelper.testSort("ImprovedMergeSortTopDown",new ImprovedMergeSortTopDown(),arr8); //11ms
        SortHelper.testSort("MergeSortBottomUp",new MergeSortBottomUp(),arr9); //12ms
    }
}
