package cn.fanyetu.design.behavior.templatemethod.simple;

/**
 * 定期账户，充当具体子类
 *
 * Created by zhanghaonan on 2017/5/11.
 */
public class SavingAccount extends Account {

	@Override
	public void calculateInterest() {
		System.out.println("按定期账户计算利息");
	}
}
