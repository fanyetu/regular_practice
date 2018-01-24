package cn.fanyetu.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码生成接口
 *
 * @author zhanghaonan
 * @date 2018/1/23
 */
public interface ValidateCodeGenerator {

    ValidateCode generateCode(ServletWebRequest request);
}
