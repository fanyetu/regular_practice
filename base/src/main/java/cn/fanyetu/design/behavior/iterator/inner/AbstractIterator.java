package cn.fanyetu.design.behavior.iterator.inner;

/**
 * 抽象迭代器
 *
 * Created by Administrator on 2017/4/16.
 */
public interface AbstractIterator {

    /**
     * 移至下一个元素
     */
    void next();

    /**
     * 是否是第一个元素
     * @return 是/否
     */
    boolean isFirst();

    /**
     * 移至上一个元素
     */
    void previous();

    /**
     * 是否是最后一个元素
     * @return
     */
    boolean isLast();

    /**
     * 获取下一个元素
     * @return
     */
    Object getNextItem();

    /**
     * 获取上一个元素
     * @return
     */
    Object getPreviousItem();

}
