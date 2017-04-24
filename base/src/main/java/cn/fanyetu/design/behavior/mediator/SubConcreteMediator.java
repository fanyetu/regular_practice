package cn.fanyetu.design.behavior.mediator;

/**
 * Created by zhanghaonan on 2017/4/24.
 */
public class SubConcreteMediator extends ConcreteMediator {

	private Label label;

	public void setLabel(Label label) {
		this.label = label;
	}

	@Override
	public void componentChange(Component component) {
		if (component == button){
			System.out.println("单击新增按钮");
			list.update();
			textBox.update();
			comboBox.update();
			label.update();
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
}
