package cn.fanyetu.jvm.classload;

/**
 * 被动引用演示2
 * <p>
 * 通过数组定义来引用类，不会触发此类的初始化
 *
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class NotInitializationDemo2 {

    public static void main(String[] args) {
        SuperClass[] superClasses = new SuperClass[10];

        /*
        out:
        无
         */
    }
}
