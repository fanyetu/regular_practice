package cn.fanyetu.sort.common;

import org.junit.Test;

/**
 * SortHelper测试
 */
public class SortHelperTest {
    @Test
    public void generateRandomArray() throws Exception {
        int n = 1000;
        Integer[] arr = SortHelper.generateRandomArray(n, 0, n);
        SortHelper.printArray(arr);

    }

    @Test
    public void isSorted() throws Exception {

    }

    @Test
    public void printArray() throws Exception {
    }

    @Test
    public void less() throws Exception {
    }

    @Test
    public void swap() throws Exception {
    }

}
