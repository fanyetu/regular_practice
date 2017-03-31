package cn.fanyetu.design.create.abstractFactory;

/**
 * 抽象工厂模式
 * 客户端
 * @author zhanghaonan
 *
 */
public class Client {
	
	public static void main(String[] args) {
		
		//创建某一个产品簇的工厂类
		Provider provider = new SmsProvider();
		
		//通过该工厂类生成相应的产品
		Accept accept = provider.produceAccept();
		accept.accept();
		
		Sender sender = provider.produceSender();
		sender.send();
	}

}
