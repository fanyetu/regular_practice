package cn.fanyetu.sort.quick;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 优化后的归并排序
 */
public class ImprovedQuickSort implements Sort {

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
//        if (l >= r) {
//            return;
//        }
        // improved 在数组长度很短的时候进行插入排序
        if (r - l <= 15) {
            SortHelper.insertionSort(arr, l, r);
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
//        T v = arr[l];
        // 在基准元素为最左侧元素的时候，如果排序一个近乎有序的数组，
        // 那么很有可能每次partition的左侧数组为空，而右侧数组很大，导致递归调用链的长度接近于n
        // 而且每次partition的复杂度又是n，所以快速排序就退化成了一个n2级别的算法
        // improved 基准元素通过随机选择，减少快速排序退化成n2级别算法的几率
        SortHelper.swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        T v = arr[l];

        /*// arr[l+1...j]<v && arr[j+1...i)>v ，初始情况前后两个数组都为空
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            // 如果i位置的元素比v大，那么i就是处理大于v这个数组里面的，直接i++即可
            // 如果i位置的元素比v小，那么将i元素和大于v这个数组的第一个元素进行交换，同时将j++即可
            if (SortHelper.less(arr[i], v)) {
                SortHelper.swap(arr, j + 1, i);
                j++;
            }
        }*/

        // improved 之前的partition的方式，如果数组中重复元素非常多的话，partition之后的两个数组就会非常的不平衡
        // 因为有可能一边的数组包含了大量的重复元素，进过改进后，将和v相等的元素随机的存放在partition后的两个数组中
        // 防止了某一边的数组过大。
        // arr[l+1...i)<=v && arr(j...r]>=v
        int i = l + 1, j = r;
        while (true) { // i向前移动，j向后移动
            while (i <= r && SortHelper.less(arr[i], v)) i++;
            while (j >= l + 1 && SortHelper.less(v, arr[j])) j--;
            if (i > j) {
                break;
            }
            SortHelper.swap(arr, i, j);
            i++;
            j--;
        }

        // 当整个数组遍历完成之后，将v这个元素和小于v的数组的最后一个元素进行交换即可
        SortHelper.swap(arr, l, j);

        return j;
    }
}
