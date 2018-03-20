package cn.fanyetu.jvm.classload;

/**
 * 被动引用演示3
 * <p>
 * 常量在虚拟机编译优化的时候，放到了本类的常量池中，不存在堆ConstClass的引用了，所以没有初始化这个类
 *
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class NotInitializationDemo3 {

    public static void main(String[] args) {
        System.out.println(ConstClass.HELLO);

        /*
        out:
        hello world
         */
    }
}
