package cn.fanyetu.design.create.abstractFactory;

/**
 * Sender的产品实现
 * @author zhanghaonan
 *
 */
public class MailSender implements Sender {

	@Override
	public void send() {
		System.out.println("这是MailSender的send方法");
	}

}
