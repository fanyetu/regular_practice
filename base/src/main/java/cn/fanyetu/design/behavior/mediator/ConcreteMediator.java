package cn.fanyetu.design.behavior.mediator;

/**
 * 具体的中介者类
 *
 * Created by zhanghaonan on 2017/4/24.
 */
public class ConcreteMediator extends Mediator {

	//维持各个同时对象的引用
	private Button button;
	private List list;
	private TextBox textBox;
	private ComboBox comboBox;

	//封装各同事对象之间的交互
	@Override
	public void componentChange(Component component) {
		if (component == button){
			System.out.println("单击新增按钮");
			list.update();
			textBox.update();
			comboBox.update();
		} else if (component == list){
			System.out.println("从列表中选择客户");
			list.select();
			comboBox.select();
			textBox.setText();
		} else if (component == comboBox){
			System.out.println("从组合框中选择客户");
			comboBox.select();
			list.select();
			textBox.setText();
		}
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setTextBox(TextBox textBox) {
		this.textBox = textBox;
	}

	public void setComboBox(ComboBox comboBox) {
		this.comboBox = comboBox;
	}
}
