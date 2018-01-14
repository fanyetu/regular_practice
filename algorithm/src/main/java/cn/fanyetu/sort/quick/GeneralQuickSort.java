package cn.fanyetu.sort.quick;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 快速排序
 *
 * O(NlogN)
 *
 * 有可能会退化为n2级别的算法
 *
 */
public class GeneralQuickSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }

    /**
     * 对arr[l...r]进行快速排序
     *
     * @param arr
     * @param l
     * @param r
     * @param <T>
     */
    private <T extends Comparable<T>> void quickSort(T[] arr, int l, int r) {

        // 递归结束条件
        if (l >= r) {
            return;
        }

        int p = partition(arr, l, r);

        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    /**
     * 对arr[l...r]进行partition操作
     *
     * @param arr
     * @param l
     * @param r
     * @param <T>
     * @return 返回p，使得arr[l...p-1]<arr[p]并且arr[p+1...r]>arr[p]
     */
    private <T extends Comparable<T>> int partition(T[] arr, int l, int r) {
        // 基准元素
        T v = arr[l];

        // arr[l+1...j]<v && arr[j+1...i)>v ，初始情况前后两个数组都为空
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            // 如果i位置的元素比v大，那么i就是处理大于v这个数组里面的，直接i++即可
            // 如果i位置的元素比v小，那么将i元素和大于v这个数组的第一个元素进行交换，同时将j++即可
            if (SortHelper.less(arr[i], v)) {
                SortHelper.swap(arr, j + 1, i);
                j++;
            }
        }

        // 当整个数组遍历完成之后，将v这个元素和小于v的数组的最后一个元素进行交换即可
        SortHelper.swap(arr, l, j);

        return j;
    }
}
