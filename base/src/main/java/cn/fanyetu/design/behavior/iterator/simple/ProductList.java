package cn.fanyetu.design.behavior.iterator.simple;

import java.util.List;

/**
 * 具体聚合类
 *
 * Created by Administrator on 2017/4/16.
 */
public class ProductList extends AbstractObjectList {

    public ProductList(List<Object> list) {
        super(list);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
