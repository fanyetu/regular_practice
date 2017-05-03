package cn.fanyetu.design.behavior.state.share;

/**
 * 开关类，充当环境类
 *
 * Created by zhanghaonan on 2017/5/3.
 */
public class Switch {

	//就是共享的state状态
	private static State state,onState,offState;//定义三个静态的状态对象
	private String name;

	public Switch(String name) {
		this.name = name;
		onState = new OnState();
		offState = new OffState();
		state = onState;
	}

	public void setState(State state) {
		Switch.state = state;
	}

	public static State getState(String type) {
		if ("on".equalsIgnoreCase(type)){
			return onState;
		} else {
			return offState;
		}
	}

	//打开开关
	public void on() {
		System.out.print(name);
		state.on(this);
	}

	//关闭开关
	public void off() {
		System.out.print(name);
		state.off(this);
	}
}
