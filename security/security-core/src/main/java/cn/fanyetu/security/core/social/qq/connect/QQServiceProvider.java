package cn.fanyetu.security.core.social.qq.connect;

import cn.fanyetu.security.core.social.qq.api.QQ;
import cn.fanyetu.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * QQ服务提供商实现
 *
 * @author zhanghaonan
 * @date 2018/4/5
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    /**
     * 跳转到用户授权地址的url
     */
    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 获取用户accessToken的url
     */
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    public QQServiceProvider(String appId, String appSecret) {
        //这里直接使用的social提供的oauth2Template，其中封装了OAuth中的从用户授权到获取到accessToken的全部过程
        super(new OAuth2Template(appId, appSecret, AUTHORIZE_URL, TOKEN_URL));
    }

    /**
     * 获取自己实现的API接口
     * @param accessToken
     * @return
     */
    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
