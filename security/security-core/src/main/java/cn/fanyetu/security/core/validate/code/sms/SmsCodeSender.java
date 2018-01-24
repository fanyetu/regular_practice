package cn.fanyetu.security.core.validate.code.sms;

/**
 * @author zhanghaonan
 * @date 2018/1/24
 */
public interface SmsCodeSender {

    void send(String phone, String code);
}
