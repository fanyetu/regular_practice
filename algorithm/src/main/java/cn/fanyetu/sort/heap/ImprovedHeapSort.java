package cn.fanyetu.sort.heap;

import cn.fanyetu.sort.common.Sort;

/**
 * 对普通堆排序的优化
 * 主要优化了将数组元素放入堆中的过程，简化为了时间复杂度为O(n)的方式
 * <p>
 * Created by zhanghaonan at 2017/10/18
 */
public class ImprovedHeapSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        MaxHeap<T> heap = new MaxHeap<>(arr);

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }
}
