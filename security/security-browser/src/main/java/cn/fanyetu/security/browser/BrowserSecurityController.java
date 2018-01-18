package cn.fanyetu.security.browser;

import cn.fanyetu.security.browser.support.SimpleResponse;
import cn.fanyetu.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhanghaonan
 * @date 2018/1/18
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 当spring在截获请求的时候会将请求存放到httpSessionRequestCache中
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    // spring提供的重定向工具类
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证的时候跳转到本方法
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) // 返回401
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            // 从什么地方跳转到我们当前的页面
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("redirectUrl: " + redirectUrl);

            // 如果是从页面跳转过来的，那么重定向到登录页面
            if (StringUtils.isNotBlank(redirectUrl) && redirectUrl.endsWith(".html")) {
                // 重定向的地址可以由使用者自定义
                redirectStrategy.sendRedirect(request, response,
                        securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("该请求需要认证，请引导用户前往登录页面");
    }
}
