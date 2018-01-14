package cn.fanyetu.search.binary;

/**
 * 寻访方式实现的二分查找法
 * <p>
 * Created by zhanghaonan at 2017/10/23
 */
public class LoopBinarySearch {

    public <T extends Comparable<T>> int search(T[] arr, T target) {
        int n = arr.length;
        // 在[l...r]范围内查找
        int l = 0, r = n - 1;

        while (l <= r) {

            int mid = (l + r) / 2;
            if (arr[mid].equals(target)) {
                return mid;
            }

            if (target.compareTo(arr[mid]) < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

}
