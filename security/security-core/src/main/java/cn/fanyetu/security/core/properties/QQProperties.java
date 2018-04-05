package cn.fanyetu.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * socialProperties中已经提供了appId和appSecret属性
 *
 * @author zhanghaonan
 * @date 2018/4/5
 */
public class QQProperties extends SocialProperties {

    /**
     * 当前服务提供上的id
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
