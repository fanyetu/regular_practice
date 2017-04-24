package cn.fanyetu.design.behavior.mediator;

/**
 * 标签类，具体同事类
 *
 * Created by zhanghaonan on 2017/4/24.
 */
public class Label extends Component {
	@Override
	public void update() {
		System.out.println("文本标签内容改变，客户总数+1");
	}
}
