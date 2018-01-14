package cn.fanyetu.sort.bubble;

import cn.fanyetu.sort.common.SortHelper;

/**
 * Created by zhanghaonan at 2017/10/18
 */
public class Test {

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = SortHelper.generateRandomArray(n, 0, n);

        SortHelper.testSort("BubbleSort",new BubbleSort(),arr); // 98ms
    }
}
