package cn.fanyetu.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 拦截注解的切面
 * @author Administrator
 *
 */
@Aspect
@Component
public class GreetingAspect2 {

	@Around("@annotation(cn.fanyetu.aop.Tag)")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		before();
		Object result = pjp.proceed();
		after();
		return result;
	}
	
	private void before(){
		System.out.println("aspectJ annotation Before");
	}
	
	private void after(){
		System.out.println("aspectJ annotation After");
	}
	
}
