package cn.fanyetu.sort.bubble;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 冒泡排序
 * <p>
 * Created by zhanghaonan at 2017/10/21
 */
public class BubbleSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (SortHelper.less(arr[j], arr[i])) {
                    SortHelper.swap(arr, i, j);
                }
            }
        }
    }
}
