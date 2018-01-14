package cn.fanyetu.sort.merge;

import cn.fanyetu.sort.common.Sort;

import static cn.fanyetu.sort.merge.MergeSortHelper.merge;

/**
 * 归并排序
 * <p>
 * 自底向上的归并排序
 */
public class MergeSortBottomUp implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;

        // 定义要归并排序的数组的长度大小1,2,4,8,16...
        for (int sz = 1; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) { //循环数组，对两两子数组进行归并
                // 对arr[i...i + sz -1] 和 arr[i + sz...i + sz + sz -1] 进行归并，并且保证i+2*sz-1 < n-1
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }
}
