package cn.fanyetu.design.behavior.iterator.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 * <p>
 * Created by Administrator on 2017/4/16.
 */
public class Client {

    public static void main(String[] args) {
        List<Object> products = new ArrayList<>();
        products.add("倚天剑");
        products.add("屠龙刀");
        products.add("断肠草");
        products.add("葵花宝典");
        products.add("四十二章经");

        AbstractObjectList list;
        AbstractIterator iterator;

        //创建聚合器
        list = new ProductList(products);
        //创建迭代器
        iterator = list.createIterator();

        System.out.println("正向遍历");
        while (!iterator.isLast()) {
            Object item = iterator.getNextItem();
            System.out.println(item.toString());
            iterator.next();
        }

        System.out.println("=============================");

        System.out.println("逆向遍历");
        while (!iterator.isFirst()){
            Object item = iterator.getPreviousItem();
            System.out.println(item.toString());
            iterator.previous();
        }
    }
}
