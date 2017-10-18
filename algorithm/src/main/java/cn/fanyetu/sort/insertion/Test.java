package cn.fanyetu.sort.insertion;

import cn.fanyetu.sort.common.SortHelper;
import cn.fanyetu.sort.selection.SelectionSort;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr1 = SortHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length); // 拷贝数组
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortHelper.testSort("normalInsertionSort", new GeneralInsertionSort(), arr1); // 14059ms
        SortHelper.testSort("improvedInsertionSort", new ImprovedInsertionSort(), arr2); // 9917ms
        SortHelper.testSort("selectionSort", new SelectionSort(), arr3); // 11689ms

        System.out.println("======================");

        // 对近乎有序的数组进行排序
        Integer[] arr4 = SortHelper.generateNearlyOrderedArray(n, 100);
        Integer[] arr5 = Arrays.copyOf(arr4, arr4.length);
        Integer[] arr6 = Arrays.copyOf(arr4, arr4.length);
        Integer[] arr7 = Arrays.copyOf(arr4, arr4.length);

        SortHelper.testSort("normalInsertionSort", new GeneralInsertionSort(), arr4); // 36ms
        SortHelper.testSort("improvedInsertionSort", new ImprovedInsertionSort(), arr5); // 30ms
        SortHelper.testSort("selectionSort", new SelectionSort(), arr6); // 7868ms

        // SortHelper的插入排序的静态方法
        SortHelper.insertionSort(arr7,0,n-1);
        SortHelper.isSorted(arr7);
    }
}
