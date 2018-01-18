package cn.fanyetu.security.browser.support;

/**
 * @author zhanghaonan
 * @date 2018/1/18
 */
public class SimpleResponse {

    private Object data;

    public SimpleResponse(Object data) {
        this.data = data;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
