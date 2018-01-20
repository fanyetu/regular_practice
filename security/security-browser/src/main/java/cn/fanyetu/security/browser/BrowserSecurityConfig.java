package cn.fanyetu.security.browser;

import cn.fanyetu.security.browser.authentication.AuthenticationFailureHandler;
import cn.fanyetu.security.browser.authentication.AuthenticationSuccessHandler;
import cn.fanyetu.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
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
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form") // 告诉spring security登录请求地址
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage())
                .permitAll() // 对该页面开发所有访问
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
