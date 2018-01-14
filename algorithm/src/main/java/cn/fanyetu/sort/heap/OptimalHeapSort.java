package cn.fanyetu.sort.heap;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 在优化的堆排序上再进行优化，不使用额外的空间，直接使用原始的数组
 * 这样堆排序在时间复杂度上是O(nlogn)，空间复杂度上可以达到O(1)
 * <p>
 * Created by zhanghaonan at 2017/10/18
 */
public class OptimalHeapSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        /*
        1.先堆传入的数组进行heapify操作，将其转换为一个最大堆
        2.将堆中的第一个元素和数组末尾的元素交换
        3.对除了刚才交换的元素之外的数组的第一个元素进行shiftDown操作
        4.再将最大堆的第一个元素和数组的倒数第二个交换
        5.重复上述步骤
         */

        int n = arr.length;
        // heapify
        // 可以使用自定义的MaxHeap实现，需要将MaxHeap的实现改为从第0个元素开始，这里直接在本类中实现
        // 第一个不是叶子节点的节点位置为(count-1)/2
        for (int i = (n - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }

        // 将后面的元素和第一个元素进行交换，并且对第一个元素进行下沉操作
        for (int i = n - 1; i > 0; i--) {
            SortHelper.swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    /**
     * shiftDown下沉操作
     *
     * @param arr
     * @param n
     * @param k
     * @param <T>
     */
    private <T extends Comparable<T>> void shiftDown(T[] arr, int n, int k) {
        // 左边的叶子节点的位置为2*k+1
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && SortHelper.less(arr[j], arr[j + 1])) {
                j += 1;
            }
            if (SortHelper.less(arr[j], arr[k])) {
                break;
            }
            SortHelper.swap(arr, k, j);
            k = j;
        }
    }
}
