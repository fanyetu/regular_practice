package cn.fanyetu.sort.heap;

import cn.fanyetu.sort.common.Sort;

/**
 * 使用堆进行排序
 * <p>
 * 堆的主要作用并不是用于排序，而是用于动态的增减元素
 *
 * Created by zhanghaonan at 2017/10/18
 */
public class NormalHeapSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        MaxHeap<T> heap = new MaxHeap<>(n);

        // 将数组元素压如堆中
        for (int i = 0; i < n; i++) {
            heap.insert(arr[i]);
        }

        // 从堆中取出元素顺序放入数组中即可
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }
}
