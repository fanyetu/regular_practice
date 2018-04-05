package cn.fanyetu.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 该配置可以直接配置在application.properties中
 *
 * @author zhanghaonan
 * @date 2018/1/18
 */
@ConfigurationProperties(prefix = "fanyetu.security")
public class SecurityProperties {

    private SocialProperties social = new SocialProperties();

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
