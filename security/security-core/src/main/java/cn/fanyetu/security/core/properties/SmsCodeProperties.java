package cn.fanyetu.security.core.properties;

/**
 * 短信验证码配置
 *
 * @author zhanghaonan
 * @date 2018/1/24
 */
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
