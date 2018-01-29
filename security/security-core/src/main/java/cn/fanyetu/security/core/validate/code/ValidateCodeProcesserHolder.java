package cn.fanyetu.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhanghaonan
 * @date 2018/1/29
 */
@Component
public class ValidateCodeProcesserHolder {

    @Autowired
    private Map<String, ValidateCodeProcesser> validateCodeProcessers;

    public ValidateCodeProcesser findValidateCodeProcesser(ValidateCodeType type) {
        return findValidateCodeProcesser(type.toString().toLowerCase());
    }

    public ValidateCodeProcesser findValidateCodeProcesser(String type) {
        String name = type.toLowerCase() + ValidateCodeProcesser.class.getSimpleName();
        ValidateCodeProcesser processer = validateCodeProcessers.get(name);
        if (null == processer) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processer;
    }
}
