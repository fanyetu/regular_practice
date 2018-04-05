package cn.fanyetu.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * spring social ServiceProvider中的API实现，用于获取服务提供上的用户信息
 * <p>
 * AbstractOAuth2ApiBinding抽象类中提供了两个属性，分别是accessToken和restTemplate
 * 其中accessToken就是服务提供商返回的令牌，而restTemplate就是向服务提供商请求令牌时使用的http客户端
 *
 * @author zhanghaonan
 * @date 2018/4/5
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 调用QQ接口获取用户信息的地址，其中包含了三个参数，分别是accessToken、appId和openId
     * 这里的地址我们只用展示appId和openId即可，accessToken参数父类会自动为我们加上
     */
    private static final String
            GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * 调用QQ接口获取用户openId的接口
     */
    private static final String GET_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s ";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 构造函数
     *
     * @param accessToken
     * @param appId
     */
    public QQImpl(String accessToken, String appId) {
        // 调用父类的构造函数，其中设置请求用户信息时token的请求类型为url参数
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;

        // 调用接口获取openId
        RestTemplate restTemplate = getRestTemplate();
        String result = restTemplate.getForObject(String.format(GET_OPENID_URL, accessToken), String.class);

        System.out.println(result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    /**
     * 实际获取用户信息
     *
     * @return
     */
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(GET_USER_INFO_URL, appId, openId);

        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);

        try {
            return objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
