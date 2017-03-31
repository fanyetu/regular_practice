package cn.fanyetu.design.create.abstractFactory;

/**
 * SmsSender��Ʒ�صĳ��󹤳�ʵ����
 * @author zhanghaonan
 *
 */
public class SmsProvider implements Provider {

	@Override
	public Sender produceSender() {
		return new SmsSender();
	}

	@Override
	public Accept produceAccept() {
		return new SmsAccept();
	}

}
