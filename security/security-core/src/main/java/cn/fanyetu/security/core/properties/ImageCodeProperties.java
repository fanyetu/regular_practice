package cn.fanyetu.security.core.properties;

/**
 * @author zhanghaonan
 */
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        setWidth(4); // 设置图形验证码默认时4位
    }

    private int width = 67;
    private int height = 23;
    private String url; // 使用逗号隔开的url列表

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
