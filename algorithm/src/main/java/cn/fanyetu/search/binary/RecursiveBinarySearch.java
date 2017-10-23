package cn.fanyetu.search.binary;

/**
 * Created by zhanghaonan at 2017/10/23
 */
public class RecursiveBinarySearch {

    public <T extends Comparable<T>> int search(T[] arr, T target) {
        return search(arr, 0, arr.length - 1, target);
    }

    public <T extends Comparable<T>> int search(T[] arr, int l, int r, T target) {
        if (l > r) {
            return -1;
        }

        int mid = (l + r) / 2;
        if (arr[mid].equals(target)) {
            return mid;
        }

        if (target.compareTo(arr[mid]) < 0) {
            return search(arr, l, mid - 1, target);
        } else {
            return search(arr, mid + 1, r, target);
        }
    }
}
