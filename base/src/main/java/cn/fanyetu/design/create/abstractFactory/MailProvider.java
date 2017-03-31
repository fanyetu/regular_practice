package cn.fanyetu.design.create.abstractFactory;

/**
 * Mail��Ʒ�صĹ�����
 * @author zhanghaonan
 *
 */
public class MailProvider implements Provider {

	@Override
	public Sender produceSender() {
		return new MailSender();
	}

	@Override
	public Accept produceAccept() {
		return new MailAccept();
	}

}
