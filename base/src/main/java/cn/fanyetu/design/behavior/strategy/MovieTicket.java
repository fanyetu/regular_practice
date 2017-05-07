package cn.fanyetu.design.behavior.strategy;

/**
 * 电影票类，充当环境类
 *
 * Created by Administrator on 2017/5/7.
 */
public class MovieTicket {

    private Discount discount;//维持一个对抽象折扣类的引用
    private double price;

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 计算折扣后的金额
     * @return
     */
    public double getPrice(){
        //调用折扣类的折扣方法
        return discount.calculate(this.price);
    }
}
