package cn.fanyetu.design.behavior.observer.simple;

/**
 * Created by zhanghaonan on 2017/5/2.
 */
public class Client {

	public static void main(String[] args) {
		AllyControllerCenter acc = new ConcreteAllyControllerCenter("华山论剑");

		Observer player1,player2,player3,player4;
		player1 = new Player("黄老邪");
		player2 = new Player("欧阳峰");
		player3 = new Player("郭靖");
		player4 = new Player("周伯通");

		acc.join(player1);
		acc.join(player2);
		acc.join(player3);
		acc.join(player4);

		player1.beAttacked(acc);
	}
}
