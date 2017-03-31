package cn.fanyetu.design.create.abstractFactory;

/**
 * 内容提供者，
 * 就是设计模式中的AbstractFactory
 * @author zhanghaonan
 *
 */
public interface Provider {
	
	/**
	 * 工厂方法，产生一个sender
	 * @return
	 */
	public Sender produceSender();
	
	/**
	 * 工厂方法，产生一个Accept
	 * @return
	 */
	public Accept produceAccept();

}
