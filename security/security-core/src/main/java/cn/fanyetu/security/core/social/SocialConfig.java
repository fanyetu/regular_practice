package cn.fanyetu.security.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author zhanghaonan
 * @date 2018/4/5
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     *   create table UserConnection (userId varchar(255) not null,
         providerId varchar(255) not null,
         providerUserId varchar(255),
         rank int not null,
         displayName varchar(255),
         profileUrl varchar(512),
         imageUrl varchar(512),
         accessToken varchar(512) not null,
         secret varchar(512),
         refreshToken varchar(512),
         expireTime bigint,
         primary key (userId, providerId, providerUserId));
         create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // 生成基于数据库的connection repository，设置加密方式为不加密
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
//        repository.setTablePrefix("t_"); // 设置数据库前缀

        return repository;
    }

    /**
     * 配置social过滤器
     *
     * @return
     */
    @Bean
    public SpringSocialConfigurer fanyetuSocialSecurityConfig() {
        return new SpringSocialConfigurer();
    }
}
