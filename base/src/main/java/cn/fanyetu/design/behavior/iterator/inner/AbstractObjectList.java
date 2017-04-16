package cn.fanyetu.design.behavior.iterator.inner;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象聚合类
 *
 * Created by Administrator on 2017/4/16.
 */
public abstract class AbstractObjectList {

    protected List<Object> list = new ArrayList<>();

    public AbstractObjectList(List<Object> list) {
        this.list = list;
    }

    /**
     * 添加一个元素
     * @param object
     */
    public void add(Object object){
        this.list.add(object);
    }

    /**
     * 删除一个元素
     * @param object
     */
    public void remove(Object object){
        this.list.remove(object);
    }

    public List<Object> getObjects(){
        return this.list;
    }

    /**
     * 声明创建迭代器对象的抽象工厂方法
     * @return
     */
    public abstract AbstractIterator createIterator();
}
