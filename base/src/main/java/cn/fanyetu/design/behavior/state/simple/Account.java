package cn.fanyetu.design.behavior.state.simple;

/**
 * 账户类，充当环境类
 * <p>
 * Created by zhanghaonan on 2017/5/3.
 */
public class Account {

	//维持一个抽象状态对象的引用
	private AccountState state;

	private String owner;//开户名
	private double balance = 0;//账户余额

	public Account(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
		this.state = new NormalState(this);//设置初始状态
		System.out.println(this.owner + "开户，初始金额为" + balance);
		System.out.println("=======================");
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	//设置状态
	public void setState(AccountState state) {
		this.state = state;
	}

	/**
	 * 存款
	 *
	 * @param amount
	 */
	public void deposit(double amount) {
		System.out.println(this.owner + "存款" + amount);
		state.deposit(amount);//调用状态对象的取款方法
		System.out.println("现在余额为" + this.balance);
		System.out.println("现在帐户状态为" + this.state.getClass().getName());
		System.out.println("---------------------------------------------");
	}

	/**
	 * 取款
	 * @param amount
	 */
	public void withdraw(double amount) {
		System.out.println(this.owner + "取款" + amount);
		state.withdraw(amount); //调用状态对象的withdraw()方法
		System.out.println("现在余额为" + this.balance);
		System.out.println("现在帐户状态为" + this.state.getClass().getName());
		System.out.println("---------------------------------------------");
	}

	public void computeInterest() {
		state.computeInterest(); //调用状态对象的computeInterest()方法
	}
}
