package cn.fanyetu.design.create.abstractFactory;

/**
 * Sender�Ĳ�Ʒʵ��
 * @author zhanghaonan
 *
 */
public class MailSender implements Sender {

	@Override
	public void send() {
		System.out.println("����MailSender��send����");
	}

}
