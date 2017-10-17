package cn.fanyetu.sort.heap;

import static cn.fanyetu.sort.common.SortHelper.less;
import static cn.fanyetu.sort.common.SortHelper.swap;

/**
 * 二叉堆（最大堆/堆有序/完全二叉树）
 * <p>
 * 使用数组实现
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
     * 取出堆中最大的元素，并保持二叉堆的特性
     * @return
     */
    public T extractMax(){
        /*
        1.将堆的第一个元素取出，其即为最大的元素
        2.将堆的最后一个元素交换到第一个元素的位置,count--
        3.对第一个元素进行shiftDown(下沉)操作
         */
        if (count <= 0){
            throw new RuntimeException("堆已经空了");
        }

        T ret = data[1]; // 因为0这个索引我们没有使用，所以第一个元素直接就是1

        swap(data,1,count);
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
     *
     * 1.判断k这个元素是否有子元素
     * 2.判断k元素子元素中最大的那一个(k可能只有一个子元素)
     * 3.将k元素和子元素中最大的那一个进行交换，并将k更新为交换后的位置
     * 4.重复上述操作，直到k没有子元素或者k比两个子元素都大
     * @param k
     */
    private void shiftDown(int k) {

    }

}
