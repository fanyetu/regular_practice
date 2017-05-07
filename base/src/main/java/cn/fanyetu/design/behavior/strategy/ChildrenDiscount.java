package cn.fanyetu.design.behavior.strategy;

/**
 * 儿童折扣类，具体的策略类
 *
 * Created by Administrator on 2017/5/7.
 */
public class ChildrenDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("儿童票");
        return price - 10;
    }
}
