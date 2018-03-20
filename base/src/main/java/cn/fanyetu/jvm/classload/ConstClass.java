package cn.fanyetu.jvm.classload;

/**
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class ConstClass {

    static {
        System.out.println("Const class init!");
    }

    public static final String HELLO = "hello world";
}
