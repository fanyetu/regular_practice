package cn.fanyetu.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 继承spring security提供的AuthenticationException
 *
 * @author zhanghaonan
 * @date 2018/1/22
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
