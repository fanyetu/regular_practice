package cn.fanyetu.jvm.classload;

/**
 * 虚拟机被动引用演示，不会出发类的初始化
 * <p>
 * 对于静态字段，只有直接定义这个字段的类才会被初始化 *
 *
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class NotInitializationDemo1 {

    public static void main(String[] args) {
        System.out.println(SubClass.value);

        /*
        out:
        Super class init!
        10
         */
    }

}
