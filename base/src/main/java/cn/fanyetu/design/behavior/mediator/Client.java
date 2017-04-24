package cn.fanyetu.design.behavior.mediator;

/**
 * 测试客户端
 *
 * Created by zhanghaonan on 2017/4/24.
 */
public class Client {

	public static void main(String[] args) {
		//定义中介者对象
		ConcreteMediator mediator = new ConcreteMediator();

		//定义同事对象
		Button button = new Button();
		List list = new List();
		ComboBox comboBox = new ComboBox();
		TextBox textBox = new TextBox();

		button.setMediator(mediator);
		list.setMediator(mediator);
		comboBox.setMediator(mediator);
		textBox.setMediator(mediator);

		mediator.setButton(button);
		mediator.setComboBox(comboBox);
		mediator.setList(list);
		mediator.setTextBox(textBox);

		button.change();
		System.out.println("============");
		list.change();
		System.out.println("============");
		comboBox.change();

	}
}
