package cn.fanyetu.sort.heap;

import static cn.fanyetu.sort.common.SortHelper.less;
import static cn.fanyetu.sort.common.SortHelper.swap;

/**
 * 索引堆
 * 将索引和数据分开存储
 *
 * @author zhanghaonan
 */
public class IndexMaxHeap<T extends Comparable> {

    private T[] data;
    /**
     * 实际的堆实在indexes中实现的，indexes存储的是元素在data数组中的索引
     */
    private Integer[] indexes;
    /**
     * 反向查找数组，使其保持性质
     * reverse[i]表示索引i在indexes(堆)中的位置
     * indexes[i] = j,reverse[j] = i;
     * indexes[reverse[i]] = i;
     * reverse[indexes[i]] = i
     */
    private Integer[] reverse;
    int count;
    int capacity;

    public IndexMaxHeap(int capacity) {
        this.capacity = capacity;
        // 初始化数据数组和索引数组，数组都是从1开始的
        this.data = (T[]) new Comparable[capacity + 1];
        this.indexes = new Integer[capacity + 1];
        // 初始化反响查找数组，在插入和取出的时候维护
        this.reverse = new Integer[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            reverse[i] = 0; // 如果indexes中元素不存在，那么在reverse就表示为0
        }
        this.count = 0;
    }

    /**
     * 获取当前堆的大小
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * 判断当前的堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 向堆中插入元素
     *
     * @param i    插入的元素的索引，从0开始
     * @param item
     */
    public void insert(int i, T item) {
        if (capacity < count + 1) {
            throw new RuntimeException("堆已经满了");
        }

        if (i + 1 < 0 || i + 1 > capacity) {
            throw new RuntimeException("输入的i值非法");
        }

        i += 1;
        data[i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1; // 维护reverse

        count++;
        shiftUp(count);
    }

    /**
     * 取出堆中最大的元素，并保持二叉堆的特性
     *
     * @return
     */
    public T extractMax() {
        if (count <= 0) {
            throw new RuntimeException("堆已经空了");
        }

        T ret = data[indexes[1]]; // 从indexes数组中取出最大元素的索引

        swap(indexes, 1, count);
        // 维护reverse数组
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 因为之后将count--了，所以就是移除了count这个元素，直接将reverse中的值设置为0
        count--;

        shiftDown(1);

        return ret;
    }

    /**
     * 取出堆中最大的元素的索引，并保持二叉堆的特性
     *
     * @return
     */
    public Integer extractMaxIndex() {
        if (count <= 0) {
            throw new RuntimeException("堆已经空了");
        }

        Integer ret = indexes[1] - 1; // 从indexes数组中取出最大元素的索引，返回的索引是从0开始的

        swap(indexes, 1, count);
        count--;

        shiftDown(1);

        return ret;
    }

    /**
     * 通过索引获取数据
     *
     * @param i
     * @return
     */
    public T getItem(int i) {
        if (!contain(i)) {
            throw new RuntimeException("传入的索引值不在当前堆中");
        }
        return data[i + 1];
    }

    /**
     * 将位置i的元素替换为新的元素
     *
     * @param i
     * @param newItem
     */
    public void change(int i, T newItem) {
        if (!contain(i)) {
            throw new RuntimeException("传入的索引值不在当前堆中");
        }

        i += 1;
        data[i] = newItem;

        // 对indexes数组中相应位置的index做shiftUp和shiftDown操作，使用反相查找
        // 找到indexes[j] = i,j表示data[i]在堆中的位置
        // 之后shiftUp(j)，然后shiftDown(j)
//        for (int j = 1; j <= count; j++) { // 使用遍历的方式，这种方式效率较低，这里使用反向查找的方式，实现O(1)的复杂度
//            if (indexes[j].equals(i)){
//                shiftUp(j);
//                shiftDown(j);
//            }
//        }

        // 使用反向查找之后，复杂度将为O(1)
        int j = reverse[i];
        shiftUp(j);
        shiftDown(j);
    }

    /**
     * 判断传入的索引值是否为堆中已有的索引
     *
     * @param i
     * @return
     */
    private boolean contain(int i) {
        if (i + 1 < 0 || i + 1 > capacity) {
            throw new RuntimeException("传入的索引值非法");
        }
        return reverse[i + 1] != 0;
    }

    /**
     * 对k这个元素进行上浮操作
     * 通过data数组判断元素的大小，在indexes数组中做数据交换
     * <p>
     * 1.判断k这个元素和父元素的大小，如果比父元素大则和父元素交换位置，并更新k为之前父元素位置，重复上述操作
     * 2.父元素索引最小为1即k > 1
     * 3.父元素索引为k/2
     *
     * @param k
     */
    private void shiftUp(int k) {
        while (k > 1 && less(data[indexes[k / 2]], data[indexes[k]])) {
            swap(indexes, k / 2, k);
            // 对indexes中元素交换之后，对reverse数组进行维护
            reverse[indexes[k / 2]] = k / 2;
            reverse[indexes[k]] = k;
            k /= 2;
        }
    }

    /**
     * 对k这个元素进行下沉操作
     * <p>
     * 1.判断k这个元素是否有子元素
     * 2.判断k元素子元素中最大的那一个(k可能只有一个子元素)
     * 3.将k元素和子元素中最大的那一个进行交换，并将k更新为交换后的位置
     * 4.重复上述操作，直到k没有子元素或者k比两个子元素都大
     *
     * @param k
     */
    private void shiftDown(int k) {
        // 2*k是k元素的左孩子
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && less(data[indexes[j]], data[indexes[j + 1]])) {
                j += 1;
            }

            // 如果j位置的子元素比k位置的父元素小，那么说明当前已经完成下沉操作，直接退出循环即可
            if (less(data[indexes[j]], data[indexes[k]])) {
                break;
            }

            // 否则，交换父元素和子元素的位置，并更新k索引
            swap(indexes, k, j);
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }
    }

}
