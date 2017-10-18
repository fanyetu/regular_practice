package cn.fanyetu.sort.heap;

import static cn.fanyetu.sort.common.SortHelper.less;
import static cn.fanyetu.sort.common.SortHelper.swap;

/**
 * 二叉堆（最大堆/堆有序/完全二叉树）
 * <p>
 * 使用数组实现
 * <p>
 * 堆的入队时间复杂度为logn,出队时间复杂度也为logn
 */
public class MaxHeap<T extends Comparable> {

    protected T[] data;
    protected int count;
    protected int capacity;

    public MaxHeap(int capacity) {
        // 在实现堆的时候我们是从数组的1这个索引开始使用的，没有使用0这个索引
        this.data = (T[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    /**
     * 优化初始化堆的方法，直接不用考虑子节点的元素
     * Heapify操作
     *
     * @param arr
     */
    public MaxHeap(T[] arr) {
        /*
        1.找到arr数组所代表的二叉树的第一个不是叶子节点的节点，即为count/2
        2.然后从第一个不是叶子结点的节点向前遍历，并依次左shiftDown的操作即可
         */
        int n = arr.length;
        this.data = (T[]) new Comparable[n + 1];
        capacity = n;
        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i]; // data数组仍然从第一个元素开始使用
        }
        count = n;

        // 对不是叶子结点的节点进行shiftDown操作
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    /**
     * 取出堆中最大的元素，并保持二叉堆的特性
     *
     * @return
     */
    public T extractMax() {
        /*
        1.将堆的第一个元素取出，其即为最大的元素
        2.将堆的最后一个元素交换到第一个元素的位置,count--
        3.对第一个元素进行shiftDown(下沉)操作
         */
        if (count <= 0) {
            throw new RuntimeException("堆已经空了");
        }

        T ret = data[1]; // 因为0这个索引我们没有使用，所以第一个元素直接就是1

        swap(data, 1, count);
        count--;

        // 将第一个位置的元素进行下沉操作
        shiftDown(1);

        return ret;
    }

    /**
     * 将元素插入到堆中
     *
     * @param item
     */
    public void insert(T item) {
        /*
        将元素插入到堆中的逻辑如下：
        1.将元素插入到堆的最后一个元素之后，并将count加1
        2.对count这个元素进行shiftUp(上浮)操作
         */
        if (capacity < count + 1) {
            throw new RuntimeException("堆已经满了");
        }

        data[count + 1] = item;
        count++;

        shiftUp(count);
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
     * 对k这个元素进行上浮操作
     * <p>
     * 1.判断k这个元素和父元素的大小，如果比父元素大则和父元素交换位置，并更新k为之前父元素位置，重复上述操作
     * 2.父元素索引最小为1即k > 1
     * 3.父元素索引为k/2
     *
     * @param k
     */
    private void shiftUp(int k) {
        while (k > 1 && less(data[k / 2], data[k])) {
            swap(data, k / 2, k);
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
            // 在此轮循环中,data[k]和data[j]交换位置(j就是子元素的位置，开始设置为左子元素，判断和右子元素的大小最终确定j)
            int j = 2 * k;
            if (j + 1 <= count && less(data[j], data[j + 1])) {
                j += 1;
            }

            // 如果j位置的子元素比k位置的父元素小，那么说明当前已经完成下沉操作，直接退出循环即可
            if (less(data[j], data[k])) {
                break;
            }

            // 否则，交换父元素和子元素的位置，并更新k索引
            swap(data, k, j);
            k = j;
        }
    }

}
