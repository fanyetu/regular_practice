package cn.fanyetu.jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author zhanghaonan
 * @date 2018/3/24
 */
public class Test {

    class GrandFather{
        void thinking(){
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather{
        void thinking(){
            System.out.println("i am father");
        }
    }

    class Son extends Father{
        void thinking(){
            MethodType methodType = MethodType.methodType(void.class);
            try {
                MethodHandle mt = MethodHandles.lookup()
                        .findSpecial(GrandFather.class, "thinking", methodType, getClass());

                mt.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * ？？？ TODO 为什么没有实现书上的调用grandfather的thinking方法呢？
     * @param args
     */
    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }
}
