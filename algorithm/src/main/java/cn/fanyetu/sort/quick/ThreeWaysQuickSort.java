package cn.fanyetu.sort.quick;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 三路快速排序
 * <p>
 * 三路快速排序对于包含大量重复元素的数组拥有很好的性能
 * <p>
 * 将数组分成 <v,=v,>v 三个部分
 */
public class ThreeWaysQuickSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        quickSort3Ways(arr, 0, n - 1);
    }

    /**
     * 三路快速排序
     *
     * @param arr
     * @param l
     * @param r
     * @param <T>
     */
    public <T extends Comparable<T>> void quickSort3Ways(T[] arr, int l, int r) {

        if (r - l <= 15) {
            SortHelper.insertionSort(arr, l, r);
            return;
        }

        // partition操作
        // 获取基准点
        SortHelper.swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        T v = arr[l];

        // 将数组分成三个部分，递归调用的时候==v的部分不需要再进行操作
        // arr[l...lt] <v, arr[lt+1...i)==v,arr[gt...r]>v
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (SortHelper.less(arr[i], v)) {
                SortHelper.swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (SortHelper.less(v, arr[i])) {
                SortHelper.swap(arr, i, gt - 1);
                gt--;
            } else { // arr[i]==v
                i++;
            }
        }

        // 将基准元素和小于v的最后一个元素交换
        SortHelper.swap(arr, l, lt);

        // 对小于v和大于v的部分再进行三路快速排序
        quickSort3Ways(arr, l, lt - 1);
        quickSort3Ways(arr, gt, r);

    }
}
