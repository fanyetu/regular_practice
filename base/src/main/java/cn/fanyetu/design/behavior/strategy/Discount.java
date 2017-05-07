package cn.fanyetu.design.behavior.strategy;

/**
 * 折扣类，充当抽象策略类
 *
 * Created by Administrator on 2017/5/7.
 */
public interface Discount {

    double calculate(double price);//计算折扣
}
