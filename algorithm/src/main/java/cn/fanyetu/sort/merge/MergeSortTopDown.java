package cn.fanyetu.sort.merge;

import cn.fanyetu.sort.common.Sort;

import static cn.fanyetu.sort.merge.MergeSortHelper.merge;

/**
 * 归并排序
 *
 * 自顶向下的归并排序
 *
 * O(NlogN)
 */
public class MergeSortTopDown implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        mergeSort(arr, 0, n - 1);
    }

    /**
     * 递归使用归并排序，对arr[l...r]的范围进行排序
     *
     * @param arr
     * @param l
     * @param r
     * @param <T>
     */
    private <T extends Comparable<T>> void mergeSort(T[] arr, int l, int r) {

        // 处理递归到底的情况
        if (l >= r) {
            return;
        }

        int middle = (l + r) / 2; // 如果l和r非常打，那么l+r可能超出int的范围

        // 将数组拆分
        mergeSort(arr, l, middle);
        mergeSort(arr, middle + 1, r);

        // 对数组进行归并
        merge(arr, l, middle, r);
    }
}
