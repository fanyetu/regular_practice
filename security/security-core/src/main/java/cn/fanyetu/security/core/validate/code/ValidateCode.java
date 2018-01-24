package cn.fanyetu.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * 验证码类
 *
 * @author zhanghaonan
 * @date 2018/1/24
 */
public class ValidateCode {

    private String code;

    private LocalDateTime expires;

    public ValidateCode(String code, LocalDateTime expires) {
        this.code = code;
        this.expires = expires;
    }

    public ValidateCode(String code, int expiresIn) {
        this.code = code;
        this.expires = LocalDateTime.now().plusSeconds(expiresIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }


    /**
     * 是否已经过期
     *
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(this.expires);
    }
}
