package cn.fanyetu.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器是不能得到方法的参数的
 *
 * @author zhanghaonan
 * @date 2018/1/15
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o) throws Exception {
        System.out.println("preHandle");

        HandlerMethod method = (HandlerMethod) o;
        System.out.println(method.getClass().getName());
        System.out.println(method.getMethod().getName());

        long start = System.currentTimeMillis();
        httpServletRequest.setAttribute("start", start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");

        Long start = (Long) httpServletRequest.getAttribute("start");

        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");

        Long start = (Long) httpServletRequest.getAttribute("start");

        System.out.println("耗时: " + (System.currentTimeMillis() - start));

        // 如果异常已经被ControllerAdvice拦截了那么这里是拿不到exception的
        System.out.println("ex is: " + e);
    }
}
