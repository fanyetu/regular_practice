package cn.fanyetu.design.behavior.templatemethod.simple;

/**
 * 账户类，充当抽象类
 *
 * Created by Administrator on 2017/5/10.
 */
public abstract class Account {

	/**
	 * 模板方法
	 * @param account
	 * @param password
	 */
	public void handle(String account, String password){
		if (!validate(account,password)){
			System.out.println("用户名密码错误");
			return;
		}
		calculateInterest();
		display();
	}

	/**
	 * 基本方法--具体方法
	 * @param account
	 * @param password
	 * @return
	 */
	public boolean validate(String account, String password){
		System.out.println("账号:"+account);
		System.out.println("密码:"+password);

		return "张三丰".equalsIgnoreCase(account) && "111111".equals(password);
	}

	/**
	 * 基本方法--抽象方法
	 */
	public abstract void calculateInterest();

	public void display(){
		System.out.println("显示利息");
	}
}
