package cn.fanyetu.design.create.abstractFactory;

public class MailAccept implements Accept{

	@Override
	public void accept() {
		System.out.println("这是Mail的Accept");
	}

}
