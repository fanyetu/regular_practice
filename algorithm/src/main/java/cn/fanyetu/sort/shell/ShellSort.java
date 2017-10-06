package cn.fanyetu.sort.shell;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 希尔排序
 * O(n3/2)
 */
public class ShellSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && SortHelper.less(arr[j], arr[j - h]); j -= h) {
                    SortHelper.swap(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
