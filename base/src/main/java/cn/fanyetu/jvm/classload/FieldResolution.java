package cn.fanyetu.jvm.classload;

/**
 * 字段解析
 *
 * @author zhanghaonan
 * @date 2018/3/20
 */
public class FieldResolution {

    interface Interface0 {
        int A = 0;
    }

    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        public static int A = 4; // 如果注释掉这句话，编译器会提示The field Sub.A is ambiguous
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }

}
