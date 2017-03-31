package cn.fanyetu.design.create.abstractFactory;

/**
 * �����ṩ�ߣ�
 * �������ģʽ�е�AbstractFactory
 * @author zhanghaonan
 *
 */
public interface Provider {
	
	/**
	 * ��������������һ��sender
	 * @return
	 */
	public Sender produceSender();
	
	/**
	 * ��������������һ��Accept
	 * @return
	 */
	public Accept produceAccept();

}
