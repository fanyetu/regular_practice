package cn.fanyetu.design.structure.flyweight;

import java.util.HashMap;

/**
 * 享元工厂类
 *
 * Created by Administrator on 2017/4/9.
 */
public class IgoChessmanFactory {

    private static IgoChessmanFactory factory = new IgoChessmanFactory();
    private static HashMap<String,IgoChessman> pool;

    private IgoChessmanFactory(){
        pool = new HashMap<>();
        WhiteIgoChessman whiteIgoChessman = new WhiteIgoChessman();
        pool.put("w",whiteIgoChessman);
        BlackIgoChessman blackIgoChessman = new BlackIgoChessman();
        pool.put("b",blackIgoChessman);
    }

    //返回享元工厂的唯一实例
    public static IgoChessmanFactory getInstance(){
        return factory;
    }

    //通过key来获取存储在享元池的享元对象
    public IgoChessman getIgoChessman(String key){
        return pool.get(key);
    }

}
