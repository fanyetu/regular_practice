package cn.fanyetu.jvm.classload;

/**
 * 类加载的初始化阶段
 *
 * 父类的<clinit>()更早的执行
 *
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class InitializeDemo {

    static class Parent{
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Sub extends Parent{
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
