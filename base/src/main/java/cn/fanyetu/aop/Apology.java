package cn.fanyetu.aop;

/**
 * 测试引入增强的接口
 * 
 * (不让GreetingImpl实现该接口，而是在程序中动态实现该接口)
 * @author Administrator
 *
 */
public interface Apology {
	
	void saySorry(String name);
	
}
