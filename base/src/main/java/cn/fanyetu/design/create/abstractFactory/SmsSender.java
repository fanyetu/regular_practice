package cn.fanyetu.design.create.abstractFactory;

/**
 * Sender�Ĳ�Ʒʵ��
 * @author zhanghaonan
 *
 */
public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("����SmsSender��send����");

	}

}
