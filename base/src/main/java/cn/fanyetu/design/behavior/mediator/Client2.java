package cn.fanyetu.design.behavior.mediator;

/**
 * Created by zhanghaonan on 2017/4/24.
 */
public class Client2 {

	public static void main(String[] args) {
		SubConcreteMediator mediator = new SubConcreteMediator();

		Button addBT = new Button();
		List list = new List();
		ComboBox cb = new ComboBox();
		TextBox userNameTB = new TextBox();
		Label label = new Label();

		addBT.setMediator(mediator);
		list.setMediator(mediator);
		cb.setMediator(mediator);
		userNameTB.setMediator(mediator);
		label.setMediator(mediator);

		mediator.setLabel(label);
		mediator.setButton(addBT);
		mediator.setComboBox(cb);
		mediator.setList(list);
		mediator.setTextBox(userNameTB);

		addBT.change();
		System.out.println("============");
		list.change();
		System.out.println("============");
		cb.change();
	}
}
