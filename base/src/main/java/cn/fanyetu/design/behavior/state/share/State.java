package cn.fanyetu.design.behavior.state.share;

/**
 * 抽象状态类
 *
 * Created by zhanghaonan on 2017/5/3.
 */
public abstract class State {

	public abstract void on(Switch s);
	public abstract void off(Switch s);
}
