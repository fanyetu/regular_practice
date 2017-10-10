package cn.fanyetu.sort.merge;

import static cn.fanyetu.sort.common.SortHelper.less;

/**
 * 归并排序工具类
 */
public class MergeSortHelper {

    /**
     * 将arr[l...middle]和arr[middle+1...r]两部分进行归并
     *
     * @param arr
     * @param l
     * @param middle
     * @param r
     */
    public static void merge(Comparable[] arr, int l, int middle, int r) {

        // 创建临时的空间
        Comparable[] aux = new Comparable[r - l + 1];

        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }

        int i = l, j = middle + 1; // 创建两部分数组的初始索引
        for (int k = l; k <= r; k++) {

            // 判断数组越界条件
            if (i > middle) { // 如果i大于了middle，说明前面的数组已经排完了，则将后面的数组全部排上去
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) { // 同上，后面的数组排完了
                arr[k] = aux[i - l];
                i++;
            } else if (less(aux[i - l], aux[j - l])) { // 判断两部分数组当前索引位置的大小，将小的部分赋值到arr的索引位置
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }

    }
}
