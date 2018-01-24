package cn.fanyetu.security.core.properties;

/**
 * 验证码相关的配置
 *
 * @author zhanghaonan
 * @date 2018/1/23
 */
public class ValidateCodeProperties {

    /**
     * 图形验证码的配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
