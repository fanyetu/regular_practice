package cn.fanyetu.jvm.clazz;

/**
 * @author zhanghaonan
 * @date 2018/3/19
 */
public class ClassSimple {

    private int m;

    public int inc() {
        return m + 1;
    }

    public int inc2() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
