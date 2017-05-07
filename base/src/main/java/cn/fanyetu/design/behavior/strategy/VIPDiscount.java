package cn.fanyetu.design.behavior.strategy;

/**
 * VIP折扣类，具体的策略类
 * <p>
 * Created by Administrator on 2017/5/7.
 */
public class VIPDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("VIP票");
        System.out.println("增加积分！");
        return price * 0.5;
    }
}
