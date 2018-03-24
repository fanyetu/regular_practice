package cn.fanyetu.jvm.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理演示
 *
 * @author zhanghaonan
 * @date 2018/3/24
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        IHello iHello = (IHello) new DynamicProxy().bind(new Hello());
        iHello.sayHello();
    }

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originObj;

        Object bind(Object originObj) {
            this.originObj = originObj;
            return Proxy.newProxyInstance(originObj.getClass().getClassLoader(), originObj.getClass().getInterfaces()
                    , this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originObj, args);
        }
    }
}
