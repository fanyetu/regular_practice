package cn.fanyetu.design.behavior.observer.simple;

/**
 * 具体观察者类
 *
 * Created by zhanghaonan on 2017/5/2.
 */
public class Player implements Observer {

	private String name;

	public Player(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void help() {
		System.out.println("坚持住，"+this.name+"来救你!");
	}

	@Override
	public void beAttacked(AllyControllerCenter acc) {
		System.out.println(this.name+"遭到攻击，请求支援!");
		acc.notifyObserver(this.name);
	}
}
