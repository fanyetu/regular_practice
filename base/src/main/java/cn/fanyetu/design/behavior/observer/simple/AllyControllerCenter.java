package cn.fanyetu.design.behavior.observer.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 战队控制中心：目标类
 *
 * Created by zhanghaonan on 2017/5/2.
 */
public abstract class AllyControllerCenter {

	protected String allyName;//战队名称
	protected List<Observer> players = new ArrayList<>();//存储战队成员

	public String getAllyName() {
		return allyName;
	}

	public void setAllyName(String allyName) {
		this.allyName = allyName;
	}

	/**
	 * 注册方法
	 * @param observer
	 */
	public void join(Observer observer){
		System.out.println(observer.getName()+"加入战队"+getAllyName());
		players.add(observer);
	}

	/**
	 * 注销方法
	 * @param observer
	 */
	public void quit(Observer observer){
		System.out.println(observer.getName()+"退出战队"+getAllyName());
		players.remove(observer);
	}

	/**
	 * 抽象通知方法
	 * @param name
	 */
	public abstract void notifyObserver(String name);
}
