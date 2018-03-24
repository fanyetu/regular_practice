package cn.fanyetu.jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author zhanghaonan
 * @date 2018/3/24
 */
public class MethodHandleTest {

    static class ClassA {
        public void println(String s) {
            System.out.println("class a: " + s);
        }
    }

    public static void main(String[] args) throws Throwable {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3);
            Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();

            // 在编译的时候，实际上时不知道这个类的实际类型的
            // 无论obj最终是哪个实现类，本方法都能调用到println方法
            getPrintlnMH(obj).invokeExact("test");
        }
    }

    /**
     * 使用java实现动态语言的特性
     *
     * @param reveiver
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    private static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        // MethodType代表方法类型
        MethodType mt = MethodType.methodType(void.class, String.class);

        // bindTo是指定方法执行时的this参数，类似js中的apply
        return MethodHandles.lookup()
                .findVirtual(reveiver.getClass(), "println", mt)
                .bindTo(reveiver);
    }


}
