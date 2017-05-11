package cn.fanyetu.design.behavior.templatemethod.simple;

/**
 * Created by zhanghaonan on 2017/5/11.
 */
public class Client {

	public static void main(String[] args) {
		Account account;
		account = new CurrentAccount();//这里可以通过反射动态生成具体的实现

		account.handle("张三丰","111111");
	}
}
