package cn.fanyetu.design.behavior.state.share;

/**
 * 打开状态
 *
 * Created by zhanghaonan on 2017/5/3.
 */
public class OnState extends State{

	@Override
	public void on(Switch s) {
		System.out.println("已经打开!");
	}

	@Override
	public void off(Switch s) {
		System.out.println("关闭!");
		s.setState(Switch.getState("off"));
	}
}
