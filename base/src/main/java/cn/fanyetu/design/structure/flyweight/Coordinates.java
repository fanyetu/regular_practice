package cn.fanyetu.design.structure.flyweight;

/**
 * 棋子对象的位置，外部状态
 *
 * Created by Administrator on 2017/4/9.
 */
public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
