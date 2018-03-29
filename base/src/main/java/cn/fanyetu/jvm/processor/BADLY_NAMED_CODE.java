package cn.fanyetu.jvm.processor;

/**
 * 测试用违反命名规则的代码
 *
 * @author zhanghaonan
 * @date 2018/3/29
 */
public class BADLY_NAMED_CODE {

    enum colors {
        red, blue, green;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }
}
