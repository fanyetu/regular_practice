package cn.fanyetu.java8.inter;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class SubClass /*extends MyClass*/ implements MyInterface1, MyInterface2 {

	@Override
	public String show() {
		return MyInterface2.super.show();
	}
}
