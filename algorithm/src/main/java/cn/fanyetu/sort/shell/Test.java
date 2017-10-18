package cn.fanyetu.sort.shell;

import cn.fanyetu.sort.common.SortHelper;
import cn.fanyetu.sort.insertion.ImprovedInsertionSort;
import cn.fanyetu.sort.insertion.GeneralInsertionSort;
import cn.fanyetu.sort.selection.SelectionSort;

import java.util.Arrays;

/**
 * 希尔排序测试
 */
public class Test {

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr1 = SortHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortHelper.testSort("normalInsertionSort", new GeneralInsertionSort(), arr1); // 14059ms
        SortHelper.testSort("improvedInsertionSort", new ImprovedInsertionSort(), arr2); // 9917ms
        SortHelper.testSort("selectionSort", new SelectionSort(), arr3); // 11689ms
        SortHelper.testSort("shellSort", new ShellSort(), arr4); // 38ms 好快
    }
}
