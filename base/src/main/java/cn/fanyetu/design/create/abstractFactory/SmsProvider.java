package cn.fanyetu.design.create.abstractFactory;

/**
 * SmsSender产品簇的抽象工厂实现类
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
