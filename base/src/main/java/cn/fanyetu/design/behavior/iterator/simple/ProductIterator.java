package cn.fanyetu.design.behavior.iterator.simple;

import java.util.List;

/**
 * 具体迭代器
 *
 * Created by Administrator on 2017/4/16.
 */
public class ProductIterator implements AbstractIterator{

    //存储具体聚合类的引用
    private ProductList productList;

    //真是存储数据的列表
    private List<Object> list;

    private int cursor1;//正向遍历的游标
    private int cursor2;//逆向遍历的游标

    public ProductIterator(ProductList list) {
        this.productList = list;
        this.list = list.getObjects();
        this.cursor1 = 0;
        this.cursor2 = this.list.size()-1;
    }

    @Override
    public void next() {
        if (cursor1 < this.list.size()){
            cursor1++;
        }
    }

    @Override
    public boolean isFirst() {
        return cursor2 == -1;
    }

    @Override
    public void previous() {
        if (cursor2 > -1){
            cursor2--;
        }
    }

    @Override
    public boolean isLast() {
        return cursor1 == this.list.size();
    }

    @Override
    public Object getNextItem() {
        return this.list.get(cursor1);
    }

    @Override
    public Object getPreviousItem() {
        return this.list.get(cursor2);
    }
}
