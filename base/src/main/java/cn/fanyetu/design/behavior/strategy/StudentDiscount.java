package cn.fanyetu.design.behavior.strategy;

/**
 * 学生折扣类，充当具体的策略类
 *
 * Created by Administrator on 2017/5/7.
 */
public class StudentDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("学生票");
        return price * 0.8;
    }
}
