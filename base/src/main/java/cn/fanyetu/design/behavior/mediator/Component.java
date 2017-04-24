package cn.fanyetu.design.behavior.mediator;

/**
 * 抽象组件类，充当抽象同事类
 *
 * Created by zhanghaonan on 2017/4/24.
 */
public abstract class Component {

	protected Mediator mediator;

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	//转发调用
	public void change(){
		mediator.componentChange(this);
	}

	public abstract void update();
}
