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
