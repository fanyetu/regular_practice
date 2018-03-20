package cn.fanyetu.jvm.classload;

/**
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class SuperClass {

    static {
        System.out.println("Super class init!");
    }

    public static int value = 10;
}
