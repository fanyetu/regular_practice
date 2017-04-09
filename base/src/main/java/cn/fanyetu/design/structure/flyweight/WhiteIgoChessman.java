package cn.fanyetu.design.structure.flyweight;

/**
 * 白色棋子类，具体的享元类
 *
 * Created by Administrator on 2017/4/9.
 */
public class WhiteIgoChessman extends IgoChessman{

    @Override
    public String getColor() {
        return "白色";
    }
}
