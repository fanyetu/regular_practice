package cn.fanyetu.security.core.validate.code;

import cn.fanyetu.security.core.properties.SecurityProperties;
import cn.fanyetu.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 实现spring 提供的OncePerRequestFilter(只执行一次过滤器)
 * 本过滤器添加在UsernamePasswordAuthenticationFilter之前
 *
 * @author zhanghaonan
 * @date 2018/1/22
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private SecurityProperties securityProperties;

    private Set<String> urls = new HashSet<>();

    // spring 提供的path的工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private ValidateCodeProcesserHolder validateCodeProcesserHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        String[] matchUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage()
                .getUrl(), ",");

        if (ArrayUtils.isNotEmpty(matchUrls)) {
            urls.addAll(Arrays.asList(matchUrls));
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {

        // 只要匹配到就进行验证
        boolean flag = false;
        String uri = request.getRequestURI();
        for (String url : urls) {
            if (pathMatcher.match(url, uri)) {
                flag = true;
                break;
            }
        }

        if (flag) {
            try {
                ValidateCodeProcesser validateCodeProcesser = validateCodeProcesserHolder.findValidateCodeProcesser
                        (ValidateCodeType.IMAGE);

                validateCodeProcesser.validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }


    public ValidateCodeProcesserHolder getValidateCodeProcesserHolder() {
        return validateCodeProcesserHolder;
    }

    public void setValidateCodeProcesserHolder(ValidateCodeProcesserHolder validateCodeProcesserHolder) {
        this.validateCodeProcesserHolder = validateCodeProcesserHolder;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

}
