package cn.fanyetu.design.create.abstractFactory;

/**
 * ���󹤳�ģʽ
 * �ͻ���
 * @author zhanghaonan
 *
 */
public class Client {
	
	public static void main(String[] args) {
		
		//����ĳһ����Ʒ�صĹ�����
		Provider provider = new SmsProvider();
		
		//ͨ���ù�����������Ӧ�Ĳ�Ʒ
		Accept accept = provider.produceAccept();
		accept.accept();
		
		Sender sender = provider.produceSender();
		sender.send();
	}

}
