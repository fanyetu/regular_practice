package cn.fanyetu.jvm.outofmemory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib使方法区出现内存溢出异常
 *
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author zhanghaonan
 * @date 2018/3/11
 */
public class JavaMethodAreaOOM {

    /*
    java.lang.OutOfMemoryError: PermGen space
     */
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws
                        Throwable {
                    return methodProxy.invoke(o, objects);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{

    }
}
