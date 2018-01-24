package cn.fanyetu.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送实现
 *
 * 如果容器已经存在了smsCodeSender那么就不初始化这个bean
 *
 * @author zhanghaonan
 * @date 2018/1/24
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String phone, String code) {
        logger.info("向手机" + phone + "发送短信验证码[" + code + "]");
    }
}
