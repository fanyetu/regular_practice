package cn.fanyetu.design.structure.flyweight;

/**
 * 抽象享元类：围棋棋子
 * <p>
 * Created by Administrator on 2017/4/9.
 */
public abstract class IgoChessman {

    public abstract String getColor();

    public void display(Coordinates coordinates) {
        System.out.println("棋子颜色：" + this.getColor() + ",棋子位置：(" + coordinates.getX() + "," + coordinates.getY() + ")");
    }
}
