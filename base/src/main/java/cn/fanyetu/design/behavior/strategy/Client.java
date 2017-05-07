package cn.fanyetu.design.behavior.strategy;

/**
 * Created by Administrator on 2017/5/7.
 */
public class Client {

    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;

        mt.setPrice(originalPrice);
        System.out.println("原始价格为：" + originalPrice);
        System.out.println("---------------------");

        Discount discount;
        discount = new VIPDiscount();
        mt.setDiscount(discount);//注入具体的策略类

        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
