package cn.fanyetu.design.behavior.mediator;

/**
 * 文本框类，具体同事类
 *
 * Created by zhanghaonan on 2017/4/24.
 */
public class TextBox extends Component {

	@Override
	public void update() {
		System.out.println("客户信息增加成功后文本框清空");
	}

	public void setText(){
		System.out.println("文本框显示：小龙女");
	}
}
