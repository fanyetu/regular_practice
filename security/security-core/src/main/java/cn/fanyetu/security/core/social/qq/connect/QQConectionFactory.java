package cn.fanyetu.security.core.social.qq.connect;

import cn.fanyetu.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 组装social connection的工厂
 *
 * @author zhanghaonan
 * @date 2018/4/5
 */
public class QQConectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * 通过我们自定义的serviceProvider和adaptor进行配置
     *
     * @param providerId
     * @param appId
     * @param appSecret
     */
    public QQConectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
