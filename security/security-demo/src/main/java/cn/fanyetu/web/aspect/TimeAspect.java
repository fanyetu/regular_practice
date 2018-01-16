package cn.fanyetu.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zhanghaonan
 * @date 2018/1/15
 */
@Aspect
@Component
public class TimeAspect {

    @Around(value = "execution(* cn.fanyetu.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        Arrays.stream(args).forEach(arg -> System.out.println("arg is " + arg));

        long start = System.currentTimeMillis();

        Object object = pjp.proceed();

        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        System.out.println("time aspect end");

        return object;
    }

}
