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

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
