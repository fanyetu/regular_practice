package cn.fanyetu.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器和instanceof关键字演示
 * <p>
 * 类加载器和类本身确定了一个类在java虚拟机中的唯一性
 *
 * @author zhanghaonan
 * @date 2018/3/21
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);

//                    System.out.println(fileName);
//                    System.out.println(is);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object object = myLoader.loadClass("cn.fanyetu.jvm.classloader.ClassLoaderTest").newInstance();

        System.out.println(object.getClass());
        System.out.println(object instanceof cn.fanyetu.jvm.classloader.ClassLoaderTest);

        /*
        class cn.fanyetu.jvm.classloader.ClassLoaderTest
        false
         */
    }
}
