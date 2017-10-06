package cn.fanyetu.sort.selection;

import cn.fanyetu.sort.common.SortHelper;

/**
 * 测试选择排序
 * O(n2)
 */
public class Test {

    public static void main(String[] args) {

        int n = 10000;
        Integer[] arr = SortHelper.generateRandomArray(n, 0, n);

        SortHelper.testSort("selection sort",new SelectionSort(),arr); // 98ms

    }
}
