package cn.fanyetu.design.create.abstractFactory;

/**
 * Sender的产品实现
 * @author zhanghaonan
 *
 */
public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("这是SmsSender的send方法");

	}

}
