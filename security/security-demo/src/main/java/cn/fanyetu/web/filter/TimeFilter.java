package cn.fanyetu.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
// 直接使用component注解也可以注册这个filter，但是，这样配置的filter默认就是拦截所有请求的
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("time filter 耗时: " + (end - start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
