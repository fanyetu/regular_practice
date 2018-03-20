package cn.fanyetu.jvm.classload;

/**
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("Sub class init!");
    }
}
