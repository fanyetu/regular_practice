package cn.fanyetu.sort.selection;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 选择排序 selection sort
 * O(n2)
 *
 * 首先，找到这个数组中最小的元素，其次，将它和这个数组的第一个元素进行交换(如果第一个元素就是最小的元素那么它就和自己交换)，
 * 再次，在剩下的元素中找到最小的元素，将它与数组中的第二个元素进行交换。如此往复，直到将整个数组排序。
 * 这种方法叫做选择排序，因为它在不断地选择剩余数组中最小的元素。
 */
public class SelectionSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length; // 数组的长度
        for (int i = 0; i < n; i++) {

            // 将arr[i]和arr[i+1...n]中最小的元素进行交换
            int minIndex = i; // 最小元素的索引
            for (int j = i + 1; j < n; j++) {
                if (SortHelper.less(arr[j], arr[minIndex])) {
                    minIndex = j;
                }
            }

            SortHelper.swap(arr, i, minIndex);

        }
    }
}
