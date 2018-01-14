package cn.fanyetu.sort.merge;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

import static cn.fanyetu.sort.common.SortHelper.less;
import static cn.fanyetu.sort.merge.MergeSortHelper.merge;

/**
 * 优化版的归并排序
 *
 * 自顶向下的归并排序
 *
 *
 * - 归并排序的思想
 * - 归并排序是一种递归排序的算法，将两个有序的数组归并成一个更大的有序数组
 * - 要将一个数组排序，可以先递归的将它分成两半分别进行排序，然后将结果归并起来
 * - 归并排序的时间复杂都是NlogN级别的，但是它所需的额外空间和N成正比
 * - 归并排序分为两种，分别是自顶向下的归并排序和自底向上的归并排序
 *
 */
public class ImprovedMergeSortTopDown implements Sort {

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
//        if (l >= r) {
//            return;
//        }
        // improved 当递归到数组长度比较小的时候，转为插入排序，因为这时数组近乎有序的概率比较大
        if (r - l <= 15) {
            SortHelper.insertionSort(arr, l, r);
            return;
        }

        int middle = (l + r) / 2; // 如果l和r非常大，那么l+r可能超出int的范围

        // 将数组拆分
        mergeSort(arr, l, middle);
        mergeSort(arr, middle + 1, r);

        // improved(对近乎有序的数组有较好的提升)
        // 如果arr[middle+1] > arr[middle]说明当前这个数组就是有序的，不用再进行归并了
        if (less(arr[middle + 1], arr[middle])) {
            // 对数组进行归并
            merge(arr, l, middle, r);
        }
    }

}
