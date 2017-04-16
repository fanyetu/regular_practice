package cn.fanyetu.design.behavior.iterator.inner;

import java.util.List;

/**
 * 具体聚合类
 * <p>
 * Created by Administrator on 2017/4/16.
 */
public class ProductList extends AbstractObjectList {

    public ProductList(List<Object> list) {
        super(list);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator();
    }

    /**
     * 具体迭代器，内部类实现
     */
    private class ProductIterator implements AbstractIterator {

        private int cursor1;
        private int cursor2;

        ProductIterator() {
            this.cursor1 = 0;
            this.cursor2 = list.size() - 1;
        }

        @Override
        public void next() {
            if (cursor1 < list.size()) {
                cursor1++;
            }
        }

        @Override
        public boolean isFirst() {
            return cursor2 == -1;
        }

        @Override
        public void previous() {
            if (cursor2 > -1) {
                cursor2--;
            }
        }

        @Override
        public boolean isLast() {
            return cursor1 == list.size();
        }

        @Override
        public Object getNextItem() {
            return list.get(cursor1);
        }

        @Override
        public Object getPreviousItem() {
            return list.get(cursor2);
        }
    }
}
