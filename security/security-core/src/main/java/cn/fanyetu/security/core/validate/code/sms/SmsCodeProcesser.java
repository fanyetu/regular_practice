package cn.fanyetu.security.core.validate.code.sms;

import cn.fanyetu.security.core.properties.SecurityConstant;
import cn.fanyetu.security.core.validate.code.AbstractValidateCodeProcesser;
import cn.fanyetu.security.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 *
 * @author zhanghaonan
 * @date 2018/1/29
 */
@Component("smsValidateCodeProcesser")
public class SmsCodeProcesser extends AbstractValidateCodeProcesser<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getStringParameter(request.getRequest(), SecurityConstant
                .DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
