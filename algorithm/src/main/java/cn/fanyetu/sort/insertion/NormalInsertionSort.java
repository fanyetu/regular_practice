package cn.fanyetu.sort.insertion;

import cn.fanyetu.sort.common.Sort;

import static cn.fanyetu.sort.common.SortHelper.less;
import static cn.fanyetu.sort.common.SortHelper.swap;

/**
 * 基础版的插入排序
 * <p>
 * 因为数据的交换次数较多，所以性能较之选择排序较差
 * 因为插入排序只要找到合适的插入位置就可以提前终止内层循环，所以插入排序是在数组近乎于已排序数组的时候性能较高
 * <p>
 * 插入排序和人们整理扑克牌的思路类似，一个元素一个元素的进行操作，将每一个元素插入到之前已经有序的序列中的适当的位置。
 * O(n2)
 */
public class NormalInsertionSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 寻找arr[i]合适的插入位置（向前寻找）
//            for (int j = i; j > 0 ; j--) {
//                if (less(arr[j],arr[j-1])){
//                    swap(arr,j,j-1);
//                }else{
//                    break;
//                }
//            }
            // 和注释的代码等效
            for (int j = i; (j > 0) && less(arr[j], arr[j - 1]); j--) {
                swap(arr, j, j - 1);
            }
        }

    }
}