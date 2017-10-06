package cn.fanyetu.sort.common;

/**
 * 排序方法接口
 */
public interface Sort {

    <T extends Comparable<T>> void sort(T[] arr);
}
