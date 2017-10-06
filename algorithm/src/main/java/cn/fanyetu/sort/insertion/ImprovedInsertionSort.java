package cn.fanyetu.sort.insertion;

import cn.fanyetu.sort.common.Sort;

import static cn.fanyetu.sort.common.SortHelper.less;

/**
 * 改进后的插入排序
 * O(n2)
 * <p>
 * 通过记录需要插入的位置和不断赋值来代替不断的交换元素，提升插入排序的性能
 * <p>
 * 对于近乎有序的序列，插入排序的时间复杂度趋近于O(n)
 */
public class ImprovedInsertionSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 寻找arr[i]合适插入的位置
            T item = arr[i];
            int j; // j保持元素item应该插入的位置
            for (j = i; (j > 0) && less(item, arr[j - 1]); j--) { // 如果前一个元素比item小，则继续寻找
                arr[j] = arr[j - 1]; // 将前一个元素向后移动一格
            }
            arr[j] = item;
        }
    }
}
