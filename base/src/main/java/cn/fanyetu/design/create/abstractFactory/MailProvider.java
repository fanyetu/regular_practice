package cn.fanyetu.design.create.abstractFactory;

/**
 * Mail产品簇的工厂类
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
