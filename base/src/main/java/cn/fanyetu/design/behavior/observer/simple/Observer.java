package cn.fanyetu.design.behavior.observer.simple;

/**
 * 抽象观察者类
 *
 * Created by zhanghaonan on 2017/5/2.
 */
public interface Observer {

	String getName();
	void setName(String name);
	void help();//声明支援盟友的方法
	void beAttacked(AllyControllerCenter acc);//声明遭受攻击的方法
}
