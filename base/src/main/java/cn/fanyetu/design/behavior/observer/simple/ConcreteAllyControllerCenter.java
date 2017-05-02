package cn.fanyetu.design.behavior.observer.simple;

/**
 * 具体目标类
 *
 * Created by zhanghaonan on 2017/5/2.
 */
public class ConcreteAllyControllerCenter extends AllyControllerCenter {

	public ConcreteAllyControllerCenter(String allyName) {
		System.out.println(allyName+"战队组建成功！");
		System.out.println("=====================");
		this.allyName = allyName;
	}

	@Override
	public void notifyObserver(String name) {
		System.out.println(allyName + "战队紧急通知，盟友"+name+"遭到敌人攻击！");

		//通知每一个盟友
		for (Observer observer : players){
			if (!observer.getName().equalsIgnoreCase(name)){
				observer.help();
			}
		}
	}
}
