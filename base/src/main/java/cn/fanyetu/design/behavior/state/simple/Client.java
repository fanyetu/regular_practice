package cn.fanyetu.design.behavior.state.simple;

/**
 * Created by zhanghaonan on 2017/5/3.
 */
public class Client {

	public static void main(String[] args) {
		Account account = new Account("张三丰", 0.0);
		account.deposit(1000);
		account.deposit(1000);
		account.withdraw(2500);
		account.withdraw(2500);
		account.withdraw(1000);
	}
}
