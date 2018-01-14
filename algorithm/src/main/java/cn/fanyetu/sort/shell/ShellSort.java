package cn.fanyetu.sort.shell;

import cn.fanyetu.sort.common.Sort;
import cn.fanyetu.sort.common.SortHelper;

/**
 * 希尔排序
 * O(n3/2)
 *
 * 希尔排序是在插入排序之上改进而来。
 * 对于大规模的乱序数组，插入排序很慢，因为他只会交换相邻的元素，因此元素只能一点一点的从数组的这一端移动到另一端。
 * 希尔排序为了加快速度简单的改进了插入排序，交换不相邻的元素对数组的局部进行排序，并最终用插入排序将局部有序的数组排序。
 *
 * 希尔排序的思想
 * 使数组中任意间隔为h的元素都是有序的。这样的数组被称为h有序数组。换句话说，一个h有序数组就是h个相互独立的有序数组编织在一起组成的一个数组。
 * 在进行排序时，如果h很大，我们就能将元素移动到很远的地方，为实现更小的h有序创造方便。
 */
public class ShellSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && SortHelper.less(arr[j], arr[j - h]); j -= h) {
                    SortHelper.swap(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
