package cn.fanyetu.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaonan
 * @date 2018/4/8
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClientAApplication {

    /**
     * 返回登陆用户信息
     *
     * @param user
     * @return
     */
    @GetMapping("/user")
    public Authentication user(Authentication user) {
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(SsoClientAApplication.class, args);
    }
}
