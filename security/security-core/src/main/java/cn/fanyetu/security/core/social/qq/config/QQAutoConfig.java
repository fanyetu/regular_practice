package cn.fanyetu.security.core.social.qq.config;

import cn.fanyetu.security.core.properties.QQProperties;
import cn.fanyetu.security.core.properties.SecurityProperties;
import cn.fanyetu.security.core.social.qq.connect.QQConectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 自动配置connectionFactory
 *
 * @author zhanghaonan
 * @date 2018/4/5
 */
@Configuration
@ConditionalOnProperty(prefix = "fanyetu.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }
}
