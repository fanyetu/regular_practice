package cn.fanyetu.jvm.remoteinvoke;

/**
 * @author zhanghaonan
 * @date 2018/3/24
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadBytes(byte[] classByte){
        return defineClass(null, classByte, 0, classByte.length);
    }
}
