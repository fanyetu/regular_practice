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

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
