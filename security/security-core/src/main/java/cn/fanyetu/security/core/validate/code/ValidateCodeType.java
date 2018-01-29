package cn.fanyetu.security.core.validate.code;

import cn.fanyetu.security.core.properties.SecurityConstant;

/**
 * 验证码类型
 *
 * @author zhanghaonan
 * @date 2018/1/29
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 检验时从请求中获取的参数的名字
     */
    public abstract String getParamNameOnValidate();

}
