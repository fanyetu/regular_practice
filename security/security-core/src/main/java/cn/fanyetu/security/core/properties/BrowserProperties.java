package cn.fanyetu.security.core.properties;

/**
 * @author zhanghaonan
 * @date 2018/1/18
 */
public class BrowserProperties {

    /**
     * 登录页配置，默认为fanyetu-login.html
     */
    private String loginPage = "/fanyetu-login.html";

    /**
     * 登录验证后的处理方式
     */
    private LoginResponseType loginType = LoginResponseType.REDIRECT;

    /**
     * 记住我过期的时间
     */
    private int rememberMeSeconds = 3600;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
