package cn.fanyetu.design.behavior.state.simple;

/**
 * 抽象账户状态类，充当抽象状态类
 * <p>
 * Created by zhanghaonan on 2017/5/3.
 */
public abstract class AccountState {

	protected Account account;

	//存款
	public void deposit(double amount) {
		this.account.setBalance(this.account.getBalance() + amount);
		stateCheck();
	}

	//取款
	public void withdraw(double amount) {
		this.account.setBalance(this.account.getBalance() - amount);
		stateCheck();
	}

	//计算利息
	public void computeInterest() {
		System.out.println("计算利息");
	}

	public abstract void stateCheck();//检查状态
}
