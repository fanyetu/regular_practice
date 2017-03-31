package cn.fanyetu.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 使用@Aspect注解表示其就是一个切面类(aop中的advisor)
 * 
 * 该类无需实现任何接口，只需要定义一个方法，在该方法上标注Around(还要before、after等)注解，并在注解中使用AspectJ切点表达式
 * 方法参数是一个ProceedingJoinoint对象，在aop中成为连接点，可以通过该对象获取方法的任何信息
 * 
 * 要想启用aspectJ还需要在spring配置文件中配置
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
public class GreetingAspect {
	
	/**
	 * 
	 * 切点表达式解释:
	 * execution表示拦截方法
	 * 第一个*表示返回值任意
	 * 第二个*表示匹配该类中所有方法
	 * (..)表示参数任意
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* cn.fanyetu.aop.GreetingImpl.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		before();
		Object result = pjp.proceed();
		after();
		return result;
	}
	
	private void before(){
		System.out.println("aspectJ Before");
	}
	
	private void after(){
		System.out.println("aspectJ After");
	}
	
}
