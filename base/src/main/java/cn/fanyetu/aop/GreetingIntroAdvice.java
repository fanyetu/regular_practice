package cn.fanyetu.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * 引入增强
 * 
 * 继承自spring aop提供的DelegatingIntroductionInterceptor类，
 * 并实现需要动态实现的接口
 * @author Administrator
 *
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor
		implements Apology {
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		return super.invoke(mi);
	}

	@Override
	public void saySorry(String name) {
		System.out.println("Sorry!"+name);
	}

}
