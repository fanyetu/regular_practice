package cn.fanyetu.design.behavior.templatemethod.simple;

/**
 * 活期账户，充当具体子类
 *
 * Created by zhanghaonan on 2017/5/11.
 */
public class CurrentAccount extends Account{

	/**
	 * 实现抽象方法
	 */
	@Override
	public void calculateInterest() {
		System.out.println("按活期账户计算利息");
	}
}
