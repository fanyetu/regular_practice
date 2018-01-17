package cn.fanyetu.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhanghaonan
 * @date 2018/1/17
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 实际中使用自己实现的passwordEncoder
        return new BCryptPasswordEncoder();
    }

    /**
     * spring security基础配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        http.formLogin()
                .loginPage("/fanyetu-login.html")
                .and()
                .authorizeRequests()
                .antMatchers("/fanyetu-login.html").permitAll() // 对该页面开发所有访问
                .anyRequest()
                .authenticated();
    }
}
